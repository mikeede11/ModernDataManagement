#!/bin/bash

function fbfind(){
    csvsql --query "SELECT status_message, (num_reactions + num_comments + num_shares + num_likes + num_loves + num_wows + num_hahas + num_sads + num_angrys) AS total_popularity FROM facebookdata ORDER BY total_popularity DESC" $1 | tail -n+2 | head -n1
}