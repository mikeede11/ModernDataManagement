#!/bin/bash
alert_emitter () {
    currentDateAndTime=$(date)
    echo -e "Will emit alerts every 10 seconds\nPress [CTRL+C] to stop..\nCurrent date and time $currentDateAndTime" > dataForProducer.txt
    number=0
    currentTime=""
    while [ $number -lt 100 ] 
    do
        currentTime=$(date "+%T")
        echo "emmiting alert $number at $currentTime" >> dataForProducer.txt
        ((number++))
        sleep 10
    done
}

alert_emitter