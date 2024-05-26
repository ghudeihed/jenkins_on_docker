#!/bin/bash

# Create Docker network
docker network create jenkins

# Build the custom Jenkins image
docker build -t custom-jenkins .

# Run the Jenkins container
docker run -d --name jenkins_on_docker --network jenkins --restart=unless-stopped -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home custom-jenkins
