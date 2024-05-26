# Jenkins on Docker

This project sets up a Jenkins master and multiple Jenkins agents (slaves) using Docker and Docker Compose. It dynamically creates jobs based on Job DSL scripts provided in the `jobs` directory.

## Prerequisites

- Docker
- Docker Compose

## Directory Structure

```
jenkins_on_docker/
├── cleanup_rebuild.sh
├── docker-compose.yml
├── Dockerfile
├── Dockerfile.agent
├── init.groovy.d/
│   ├── node-setup.groovy
│   └── job-setup.groovy
├── jobs/
│   ├── seedJob1.groovy
│   ├── seedJob2.groovy
│   └── seedJob3.groovy
├── plugins.txt
├── README.md
```

## Setup Instructions

### 1. Build and Run Jenkins with Docker Compose

1. Run the cleanup and rebuild script to ensure a clean setup:

   ```sh
   ./cleanup_rebuild.sh
   ```

2. Verify that Jenkins is up and running by accessing it at `http://localhost:8080`.

### 2. Job Setup

Jobs are defined using Job DSL scripts located in the `jobs` directory. The `job-setup.groovy` script dynamically creates these jobs every time Jenkins starts.

### 3. Node Setup

Jenkins agents (slaves) are defined in the `node-setup.groovy` script. The script adds nodes to the Jenkins master during startup.

### 4. Adding or Updating Jobs

To add or update jobs, place the corresponding Job DSL scripts in the `jobs` directory. The jobs will be recreated the next time Jenkins starts.

### Important Files

- **Dockerfile**: Defines the Jenkins master image.
- **Dockerfile.agent**: Defines the Jenkins agent image.
- **docker-compose.yml**: Docker Compose configuration for setting up the Jenkins master and agents.
- **init.groovy.d/node-setup.groovy**: Groovy script for setting up Jenkins agents.
- **init.groovy.d/job-setup.groovy**: Groovy script for setting up jobs based on DSL scripts.
- **cleanup_rebuild.sh**: Script to clean up and rebuild the Docker environment.

### Example Job DSL Scripts

**jobs/seedJob1.groovy**:
```groovy
job('example-job-1') {
    description('An example job 1 created with Job DSL')
    scm {
        git('https://github.com/example/repo.git')
    }
    triggers {
        scm('H/5 * * * *')
    }
    steps {
        shell('echo "Hello, World! from Job 1"')
    }
}
```

## Troubleshooting

- **Job Creation Issues**: Ensure the `job-setup.groovy` script is correctly scanning and processing the `jobs` directory.
- **Node Setup Issues**: Verify that the `node-setup.groovy` script correctly references `Node.Mode.NORMAL` and that the agent secrets are correctly configured.
- **Docker Cache Issues**: Use the `--no-cache` option with Docker Compose to ensure a clean build.

## License

This project is licensed under the MIT License.