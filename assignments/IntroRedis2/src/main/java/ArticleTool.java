import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPooled;
import redis.clients.jedis.args.FlushMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleTool {

    static int TWENTIETH_MINUTE_IN_SECONDS = 3;
    static long ARTICLE_TIMEOUT = 3 * 1000000;
    static int VOTE_SCORE = 500000;
    static int ARTICLES_PER_PAGE = 3;
    JedisPooled jedis = new JedisPooled("localhost", 6379);
    //Jedis jedisdb = new Jedis("localhost", 6379);
        //System.out.println(jedis.get("database"));
    public static void main(String[] args) {

        ArticleTool at = new ArticleTool();

        at.postAnArticle("Jane", "title195", "http://www.youtube.com");
        at.postAnArticle("Bob", "title53", "http://www.weibo.com");
        at.postAnArticle("Joel", "title3", "http://www.instagram.com");
        at.postAnArticle("Joel","title13", "http://www.taobao.com");
        at.postAnArticle("Jane","title50", "http://www.youtube.com");
        at.voteForAnArticle("Joel", "0");
        at.voteForAnArticle("Joel", "0");
        at.voteForAnArticle("Xandra", "2");
        at.voteForAnArticle("Jane", "2");
        at.getArticlesByScore(1);
        at.printArticles();
        at.getArticlesByTime(1);
        at.printArticles();
        try {
            System.out.println("Going to sleep, good night :) zzz");
            Thread.sleep((TWENTIETH_MINUTE_IN_SECONDS * 1000) + 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Waking up, good morning :)");
        at.voteForAnArticle("Xandra", "1");
        at.addGroups("0", new String[]{"cute-cats", "os"});
        at.addGroups("1", new String[]{"cute-cats"});
        at.addGroups("2", new String[]{"os"});
        at.getGroupArticles("cute-cats", 1);
        at.printArticles();
        at.getGroupArticles("os", 1);
        at.printArticles();
        //deal with when list has less than three

    }

//    public void flushDB(){
//        jedisdb.flushDB();
//    }
    public void postAnArticle ( String user , String title , String link ){
//link , votes , title , user , now , id
        String strID = null;
        int nextID = -1;
        if((strID = jedis.get("nextID")) != null){
            nextID = Integer.parseInt(strID);
        }
        else{
            //trivially if there was no id field then there should be no docs in database
            jedis.set("nextID", "0");
            strID = "0";
            nextID = 0;
        }
        /*no need to check if nextID is already there b/c for
        this project we'll assume this is the only access point to database
        through this interface. therefore IDs will always be unique since
        we increment the nextID field right before we add the ID we just took
         */
        String nowValue = currentTimeInMicroSeconds();
        jedis.incr("nextID");
        jedis.hmset(strID, new HashMap<String, String>(Map.of( "link", link,"votes", "1",   "title", title, "user", user,"now", nowValue, "id", strID)));
        double score = Double.parseDouble(nowValue) + VOTE_SCORE;
        jedis.zadd("ArticlesByScore", score, strID);
        jedis.zadd("ArticlesByTime", Double.parseDouble(nowValue), strID);//TROUBLE??? too large
        jedis.sadd(user, strID);
        System.out.println(jedis.hgetAll(strID));
    }

    private String currentTimeInMicroSeconds(){
       return "" + (System.currentTimeMillis() * 1000);
    }

    public void voteForAnArticle( String user , String articleId ){
        if(jedis.sismember(user, articleId) || timeElapsed(articleId)){
            System.out.println("Vote Not Added - Votes: " + jedis.hget(articleId, "votes"));
            return;
        }
        else{
            jedis.zincrby("ArticlesByScore", VOTE_SCORE, articleId);
            jedis.hincrBy(articleId, "votes", 1);
            jedis.sadd(user, articleId);//I already voted I wont be able to vote again
            System.out.println("Votes: " + jedis.hget(articleId, "votes"));
        }

    }

    private boolean timeElapsed(String articleId){
        long now = System.currentTimeMillis() * 1000;
        return now > (Long.parseLong(jedis.hget(articleId, "now")) + ARTICLE_TIMEOUT);
    }

    public void getArticlesByScore(int page ){
        List<String> scores = jedis.zrevrangeByScore("ArticlesByScore", Long.MAX_VALUE, 0);
        int pageStartIndex = (page - 1) * ARTICLES_PER_PAGE;
        jedis.del("MostRecentResult");
        if (scores.size() < ARTICLES_PER_PAGE) {
            for (int i = 0; i < scores.size(); i++) {
                jedis.rpush("MostRecentResult", scores.get(i));
            }
        } else {
            jedis.rpush("MostRecentResult", scores.get(pageStartIndex), scores.get(pageStartIndex + 1), scores.get(pageStartIndex + 2)); //OUT OF BOUNDS ERROR
        }
        //jedis.rpush("MostRecentResult", scores.get(pageStartIndex), scores.get(pageStartIndex + 1), scores.get(pageStartIndex + 2)); //OUT OF BOUNDS ERROR
    }

    public void getArticlesByTime(int page ) {
        List<String> times = jedis.zrevrangeByScore("ArticlesByTime", Long.MAX_VALUE, 0);
        //System.out.println("Hello" + jedis.zcard("ArticlesByTime"));
        int pageStartIndex = (page - 1) * ARTICLES_PER_PAGE;
        jedis.del("MostRecentResult");
        if (times.size() < ARTICLES_PER_PAGE) {
            for (int i = 0; i < times.size(); i++) {
                jedis.rpush("MostRecentResult", times.get(i));
            }
        } else {
            jedis.rpush("MostRecentResult", times.get(pageStartIndex), times.get(pageStartIndex + 1), times.get(pageStartIndex + 2)); //OUT OF BOUNDS ERROR
        }
    }

    public void printArticles(){
        List<String> articlesToPrint = jedis.lrange("MostRecentResult", 0, ARTICLES_PER_PAGE);
        for(String article: articlesToPrint) {
            System.out.println(jedis.hgetAll(article));
        }
    }

    public void addGroups( String articleId , String [] groups ){
        double score = jedis.zscore("ArticlesByScore", articleId);

        for(String group: groups) {
            jedis.zadd(group,score, articleId);
        }

    }

    public void getGroupArticles(String group, int page){
        List<String> scores = jedis.zrevrangeByScore(group, Long.MAX_VALUE, 0);
        int pageStartIndex = (page - 1) * ARTICLES_PER_PAGE;
        jedis.del("MostRecentResult");
        if (scores.size() < ARTICLES_PER_PAGE) {
            for (int i = 0; i < scores.size(); i++) {
                jedis.rpush("MostRecentResult", scores.get(i));
            }
        } else {
            jedis.rpush("MostRecentResult", scores.get(pageStartIndex), scores.get(pageStartIndex + 1), scores.get(pageStartIndex + 2)); //OUT OF BOUNDS ERROR
        }
        //jedis.rpush("MostRecentResult", scores.get(pageStartIndex), scores.get(pageStartIndex + 1), scores.get(pageStartIndex + 2)); //OUT OF BOUNDS ERROR

    }
}
