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

# Set the Jenkins home directory environment variable
ENV JENKINS_HOME /var/jenkins_home

# Copy custom groovy scripts to initialize Jenkins
COPY init.groovy.d /usr/share/jenkins/ref/init.groovy.d/

# Copy the plugins.txt file
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

# Create a jobs directory in Jenkins home and copy the Job DSL scripts
RUN mkdir -p $JENKINS_HOME/jobs
COPY jobs/*.groovy $JENKINS_HOME/jobs/

# Install plugins from plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

# Set environment variables (optional)
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

# Define a default command to start Jenkins
CMD ["jenkins.sh"]
