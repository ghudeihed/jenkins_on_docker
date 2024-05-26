# Jenkins on Docker with Master and Slave Nodes

This project sets up a Jenkins master node and three slave nodes using Docker and Docker Compose.

## Prerequisites

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Setup Instructions

1. **Clone the repository**:

    ```sh
    git clone https://github.com/ghudeihed/jenkins_on_docker
    cd jenkins_on_docker
    ```

2. **Generate Agent Secrets**:

    - Start Jenkins master using Docker Compose.
    - Access Jenkins at `http://localhost:8080`.
    - Go to **Manage Jenkins > Manage Nodes and Clouds > Nodes > [Node Name] > Log** to find the secret for each agent.
    - Update the `docker-compose.yml` file with the generated secrets.

3. **Build and start the containers**:

    ```sh
    docker-compose up -d --build
    ```

4. **Access Jenkins**:

    Open a web browser and navigate to `http://localhost:8080`.

5. **Retrieve the initial admin password**:

    ```sh
    docker logs jenkins_master
    ```

## Files and Directories

- `Dockerfile`: Defines the custom Jenkins image for the master node.
- `docker-compose.yml`: Docker Compose configuration file for the master and slave nodes.
- `init.groovy.d`: Directory containing Groovy scripts for Jenkins initialization.
- `README.md`: Project documentation.

## Customization

### Adding Plugins

To add more plugins, modify the `Dockerfile`:

```Dockerfile
RUN jenkins-plugin-cli --plugins \
    blueocean \
    workflow-aggregator \
    git \
    configuration-as-code \
    job-dsl \
    <additional-plugins>
```

### Initial Configuration

Add your Groovy scripts to the `init.groovy.d` directory to customize Jenkins on startup.

Example (`init.groovy.d/basic-setup.groovy`):

```groovy
import jenkins.model.*
import hudson.model.*

println "--> Configuring Jenkins with custom setup"

def instance = Jenkins.getInstance()

// Set the number of executors
instance.setNumExecutors(2)

// Configure the system message
instance.setSystemMessage("Welcome to your custom Jenkins instance!")

// Save the state
instance.save()
```

## Managing Jenkins

### Stopping Jenkins

```sh
docker-compose down
```

### Starting Jenkins

```sh
docker-compose up -d --build
```

### Viewing Logs

```sh
docker logs jenkins_master
docker logs jenkins_slave1
docker logs jenkins_slave2
docker logs jenkins_slave3
```

## Contributing

Feel free to submit issues or pull requests if you have suggestions or improvements.

## License

This project is licensed under the MIT License.