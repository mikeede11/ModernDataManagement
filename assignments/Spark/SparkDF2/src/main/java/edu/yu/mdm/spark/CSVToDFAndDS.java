package edu.yu.mdm.spark;

import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.io.Serializable;
import java.util.Date;
import java.util.Arrays;
import java.util.Collections;
import java.io.Serializable;

import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;

import static org.apache.spark.sql.functions.*;

public class CSVToDFAndDS implements Serializable {

    /** Students may not modify the Book class in any way!
     */
    public static class Book {
        int id;
        int authorId;
        String title;
        Date releaseDate;
        String link;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAuthorId() {
            return authorId;
        }

        public void setAuthorId(int authorId) {
            this.authorId = authorId;
        }

        public void setAuthorId(Integer authorId) {
            if (authorId == null) {
                this.authorId = 0;
            }
            else {
                this.authorId = authorId;
            }
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Date getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(Date releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        @Override
        public int hashCode() { return id; } // assumption: id is unique

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            if (!(o instanceof Book)) { return false; }
            final Book that = (Book) o;
            return this.id == that.id;
        }

        @Override
        public String toString() {
            // @fixme needs more state and a StringBuilder
            return "Book: id="+id+",authorId="+authorId+", title="+title;
        }

    } // static inner class Book


    private static final long serialVersionUID = -1L;

    /** Per the requirements doc, clients will supply a filename to
     * main() in a command-line invocation.  The file name is a valid
     * path to the csv file described in the requirements doc.
     * Students may not change this behavior.  They are encouraged to
     * supply additional code so that main will implement the function
     * specified in the requirements doc.
     */

    public static void main(String[] args) {
        if (args.length != 1) {
            final String msg = "Usage: "+CSVToDFAndDS.class.getName()+
                    " nameOfInputFile";
            System.err.println(msg);
            throw new IllegalArgumentException(msg);
        }

        final String inputFileName = args[0];
        //String path = "C:\\Users\\mitch\\EdelmanMichael\\ModernDataManagement\\assignments\\Spark\\books.csv";





        SparkSession spark = SparkSession
                .builder()
                .appName("Application Name")
                .config("some-config", "some-value")
                .master("local")
                .getOrCreate();

//option("dateFormat", "MM-dd-yyyy").option("allowNumericLeadingZeroes", "true" ).
        /**PHASE 1*/
        Dataset<Row> df = spark.read().option("header", "true").csv(inputFileName);//year is dissapearing!
        df.show(5);
        df.printSchema();
        df.filter("authorId == '1'").show();
        /**END PHASE 1*/

        /**PHASE 2*/
        MapFunction<Row, Book> mapFunction = new MapFunction<Row, Book>() {
            @Override
            public Book call(Row row) throws Exception {
                Book book = new Book();
                if(row.get(3) == null || ((String)row.get(3)).equals("null") || ((String)row.get(3)).equals("")) {
                    book.setReleaseDate(null);
                }
                else {
                    String[] date = ((String)row.get(3)).split("/");
                    String year = "";
                    int temp = Integer.parseInt(date[2]);
                    if(temp <= 22) {
                        year = "20" + temp;
                    }
                    else if(temp / 10 < 10){
                        year = "19" + temp;
                    }
                    else{
                        year = temp + "";
                    }
                    int editedYear = Integer.parseInt(year);
                    String month = "";
                    int temp1 = Integer.parseInt(date[0]);
                    if(temp <= 9) {
                        month = "0" + temp;
                    }
                    else{
                        month = temp + "";
                    }
                    int editedMonth = Integer.parseInt(month);
                    book.setReleaseDate(new Date(editedYear,Integer.parseInt(date[0]),Integer.parseInt(date[1])));
                }

                book.setId(Integer.parseInt((String) row.get(0)));

                if(row.get(1) == null || ((String)row.get(1)).equals("null") || ((String)row.get(1)).equals("")) {
                    book.setAuthorId(null);
                }
                else {
                    book.setAuthorId(Integer.parseInt((String) row.get(1)));
                }
                book.setTitle((String) row.get(2));
                book.setLink((String) row.get(4));
                return book;
            }
        };
        Encoder<Book> encoder = Encoders.bean(Book.class);
        Dataset<Book> ds = df.map(mapFunction, encoder);
        ds.show(5,17);
        ds.printSchema();

        ds.collectAsList().forEach(System.out::println);
        ds.filter("authorId == '1'").show();

        /**END PHASE 2*/

        /**PHASE 3*/
        Dataset<Row> newDS = ds.withColumn("releaseDateAsString", concat_ws("-",col("releaseDate").getField("year"),col("releaseDate").getField("month"), col("releaseDate").getField("date")));
        newDS.show();
        newDS = newDS.withColumn("releaseDateAsDate", to_date(date_format(to_date(col("releaseDateAsString")),"yyyy-MM-dd")));
        newDS = newDS.drop("releaseDateAsString");
        newDS.show();
        newDS = newDS.drop("releaseDate");
        newDS.show(5,17);
        newDS.printSchema();
        newDS = newDS.sort(col("releaseDateAsDate").desc());
        newDS.show(20, 15);
        /**END PHASE 3*/
    } // main
    private static final class BookPrinter implements ForeachFunction<Book> {


        @Override
        public void call(Book book) {
            System.out.println(book);
        }
    }
} // CSVToDFAndDS