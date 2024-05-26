#!/bin/bash

# Stop all running containers
docker-compose down

# Remove all containers
docker-compose rm -f

# Remove all images related to the project
docker images -q | xargs docker rmi -f

# Build without cache
docker-compose build --no-cache

# Start the containers
docker-compose up -d

# Check Jenkins master logs
docker logs jenkins_master
