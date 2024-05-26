# Jenkins on Docker

This project sets up Jenkins on Docker using a custom Docker image with predefined plugins and configurations. You can choose to set up Jenkins using a bash script or Docker Compose.

## Prerequisites

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/) (if using Docker Compose)

## Setup Options

### Option 1: Using a Bash Script

1. **Clone the repository**:

    ```sh
    git clone https://github.com/ghudeihed/jenkins_on_docker
    cd jenkins_on_docker
    ```

2. **Make the script executable**:

    ```sh
    chmod +x setup_jenkins.sh
    ```

3. **Run the script**:

    ```sh
    ./setup_jenkins.sh
    ```

### Option 2: Using Docker Compose

1. **Clone the repository**:

    ```sh
    git clone https://github.com/ghudeihed/jenkins_on_docker
    cd jenkins_on_docker
    ```

2. **Run Docker Compose**:

    ```sh
    docker-compose up -d
    ```

## Files and Directories

- `Dockerfile`: Defines the custom Jenkins image.
- `docker-compose.yml`: Docker Compose configuration file.
- `setup_jenkins.sh`: Bash script to set up Jenkins using Docker.
- `init.groovy.d`: Directory containing Groovy scripts for Jenkins initialization.

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

## Accessing Jenkins

After starting Jenkins, open a web browser and navigate to `http://localhost:8080`. The initial admin password can be found in the Docker container logs:

```sh
docker logs jenkins_on_docker
```

## Managing Jenkins

### Stopping Jenkins

```sh
docker stop jenkins_on_docker
```

### Starting Jenkins

```sh
docker start jenkins_on_docker
```

### Viewing Logs

```sh
docker logs -f jenkins_on_docker
```

## Contributing

Feel free to submit issues or pull requests if you have suggestions or improvements.

## License

This project is licensed under the MIT License.