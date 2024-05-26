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

# Copy the plugins.txt file
COPY plugins.txt /usr/share/jenkins/ref/plugins.txt

# Install plugins from plugins.txt
RUN jenkins-plugin-cli --plugin-file /usr/share/jenkins/ref/plugins.txt

# Set environment variables (optional)
ENV JAVA_OPTS -Djenkins.install.runSetupWizard=false

# Define a default command to start Jenkins
CMD ["jenkins.sh"]
