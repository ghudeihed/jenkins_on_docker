# Use the official Jenkins base image
FROM jenkins/jenkins:lts

# Switch to the root user to install additional software
USER root

# Install any additional packages you need
RUN apt-get update && apt-get install -y \
    git \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Switch back to the Jenkins user
USER jenkins

# Copy custom groovy scripts to initialize Jenkins
COPY init.groovy.d /usr/share/jenkins/ref/init.groovy.d/

# Install Jenkins plugins
RUN jenkins-plugin-cli --plugins \
    blueocean \
    workflow-aggregator \
    git \
    configuration-as-code \
    job-dsl

# Set environment variables (optional)
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

# Define a default command to start Jenkins
CMD ["jenkins.sh"]
