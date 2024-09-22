1-Start Kafka with the -d option to run in detached mode:
docker-compose up -d

2-The above command starts Kafka container. If all is ok you can open in browser
http://localhost:3040

3-Open a terminal and execute command, it will allow you to get access to bash in container
docker container exec -it kafka /bin/bash

4-# create kafka topic with custom partitions
  kafka-topics --bootstrap-server localhost:9092 --topic <YOUR_TOPIC> --create --partitions <NUMBER>

5-# list of kafka topics
  kafka-topics --bootstrap-server localhost:9092 --list
  kafka-topics.sh --bootstrap-server localhost:9092 --describe

6-# delete kafka topic
  kafka-topics --bootstrap-server localhost:9092 --delete --topic <YOUR_TOPIC>
