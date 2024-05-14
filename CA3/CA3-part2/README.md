# Technical Report: Setting up Virtual Environment with Vagrant
# Overview

In Part 2, a virtual environment was setted up using Vagrant to execute a Spring Boot application (gradle "basic" version), focusing on virtualization with Vagrant.

## Vagrant Analysis
Vagrant defines virtual environments as code using a simple, reproducible configuration file called a Vagrantfile. 
It supports multiple virtualization and cloud providers such as VirtualBox, VMware, Hyper-V, and Docker. Vagrant includes built-in support for provisioning tools like shell scripts, enabling automated software setup. These environments can be version-controlled with Git, facilitating efficient team collaboration and ensuring consistent environments across different development machines, mitigating issues where software behaves differently across systems.

## VirtualBox Analysis
VirtualBox, available for Windows, macOS, Linux, and Solaris, provides consistent virtualization across various operating systems. It includes guest additions that improve virtual machine performance and host integration, such as shared folders, clipboard sharing, and dynamic screen resizing. VirtualBox offers flexible networking options, including NAT, bridged, host-only, and internal networking. Additionally, it provides a robust API and SDK for automation, integration with third-party tools, and virtual machine customization.


### Part 1: Initial Setup
Study Vagrantfile  in "https://bitbucket.org/pssmatos/vagrant-multi-spring-tut-demo/".
- "web" for running Tomcat and the Spring Boot application;
- "db" for executing the H2 server database.

### Part 2: Copying Vagrantfile to Your Repository
Create a directory "CA3/CA3-Part2".

```bash
cd path/to/directory/
mkdir CA3/CA3-Part2
```
Copy the Vagrantfile from the cloned repository to "CA3/CA3-Part2" directory in your repository.

```bash
cd /CA3/CA3-Part2/
git clone <repository_URL>
```

### Part 3: Updating Vagrantfile Configuration
Open the Vagrantfile in your repository.
Update the configuration to use your own Gradle version of the Spring Boot application.
Ensure that the Vagrantfile reflects the changes necessary to use the H2 server in the "db" VM.

**Vagrantfile:**

```
# See: https://manski.net/2016/09/vagrant-multi-machine-tutorial/
# for information about machine names on private network
Vagrant.configure("2") do |config|
  config.vm.box = "ubuntu/focal64"

  # This provision is common for both VMs
  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update -y
    sudo apt-get install -y iputils-ping avahi-daemon libnss-mdns unzip \
        openjdk-17-jdk-headless
    # ifconfig
  SHELL

  #============
  # Configurations specific to the database VM
  config.vm.define "db" do |db|
    db.vm.box = "ubuntu/focal64"
    db.vm.hostname = "db"
    db.vm.network "private_network", ip: "192.168.56.11"

    # We want to access H2 console from the host using port 8082
    # We want to connet to the H2 server using port 9092
    db.vm.network "forwarded_port", guest: 8082, host: 8082
    db.vm.network "forwarded_port", guest: 9092, host: 9092

    # We need to download H2
    db.vm.provision "shell", inline: <<-SHELL
      wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar
    SHELL

    # The following provision shell will run ALWAYS so that we can execute the H2 server process
    # This could be done in a different way, for instance, setting H2 as as service, like in the following link:
    # How to setup java as a service in ubuntu: http://www.jcgonzalez.com/ubuntu-16-java-service-wrapper-example
    #
    # To connect to H2 use: jdbc:h2:tcp://192.168.33.11:9092/./jpadb
    db.vm.provision "shell", :run => 'always', inline: <<-SHELL
      java -cp ./h2*.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists > ~/out.txt &
    SHELL
  end

  #============
  # Configurations specific to the webserver VM
  config.vm.define "web" do |web|
    web.vm.box = "ubuntu/focal64"
    web.vm.hostname = "web"
    web.vm.network "private_network", ip: "192.168.56.10"

    # We set more ram memory for this VM
    web.vm.provider "virtualbox" do |v|
      v.memory = 1024
    end

    # We want to access tomcat from the host using port 8080
    web.vm.network "forwarded_port", guest: 8080, host: 8080

    web.vm.provision "shell", inline: <<-SHELL, privileged: false
      # sudo apt-get install git -y
      # sudo apt-get install nodejs -y
      # sudo apt-get install npm -y
      # sudo ln -s /usr/bin/nodejs /usr/bin/node
      sudo apt install -y tomcat9 tomcat9-admin
      # If you want to access Tomcat admin web page do the following:
      # Edit /etc/tomcat9/tomcat-users.xml
      # uncomment tomcat-users and add manager-gui to tomcat user

       # Change the following command to clone your own repository!
      git clone https://github.com/jrato20/devops-23-24--PSM---1231834-.git
      cd ~/devops-23-24--PSM---1231834-/CA3/CA3-part2/react-and-spring-data-rest-basic
      chmod u+x gradlew
      ./gradlew clean build
      # To deploy the war file to tomcat9 do the following command:
      sudo cp ./build/libs/react-and-spring-data-rest-basic-0.0.1-SNAPSHOT.war /var/lib/tomcat9/webapps
    SHELL
  end
end
```

- Change the `git clone` command and insert your repository url;
- Change the `cd ~/devops-23-24--PSM---1231834-/CA3/CA3-part2/react-and-spring-data-rest-basic` command with your repository directory.


### Part 4: Vagrant commands

Use `vagrant up` command to run vagrantfile.
Open your browser and write http://<your ip>:<port> (example: 192.168.56.10:8080).

If you need to update your vagrantfile and rerun it again, you'll need to use "vagrant destroy" the VMs created previously.

```bash
# remove db VM
vagrant destroy db

#remove web VM
vagrant destroy web
```

### Part 5: Committing Changes
After this, you can commit your Vagrantfile into your repository.

## Conclusions
- Vagrant and VirtualBox are powerful tools that complement each other by providing a streamlined workflow for creating and managing virtual environments. Vagrant leverages VirtualBox's robust virtualization capabilities to ensure consistent, reproducible environments across different operating systems. By using Vagrant to define configurations as code, developers can automate the setup and provisioning of VirtualBox virtual machines, enhancing productivity and collaboration.

- During the implementation of CA3/CA3-Part2 issues were found when building Spring Data Application with vagrantfile. The first time that build succeded, was after updating vagrantfile with JDK version to 17. Second time running vagrantfile, build failed, and updating aplication.properties (after research) was necessary for the build to succeed.

# Alternative Solution Using VMware

An alternative to VirtualBox is Hyper-V, Microsoft's virtualization solution for running multiple operating systems on a single physical machine. Hyper-V provides a comprehensive set of features for creating and managing virtual machines (VMs).

## Analysis comparing Hyper-V and VirtualBox
- Features

  - Hyper-V offers advanced features tailored for enterprise environments, including live migration, Hyper-V Replica for disaster recovery, and nested virtualization. 
  - VirtualBox, while robust, focuses more on essential virtualization features like snapshots, shared folders, and USB device support but lacks some of the advanced capabilities found in Hyper-V.


- Performance
  - Hyper-V typically delivers better performance in Windows-centric environments due to its optimized hypervisor and tight integration with the Windows OS. 
  - VirtualBox performance can be lower, particularly under heavy workloads, due to its emphasis on ease of use and broad compatibility.


## Overview
In this alternative solution, we'll use Hyper-V to create and manage virtual machines for running the Spring Boot application and the H2 database.


### Part 1: Setting Up Hyper-V VMs

1. Enable Hyper-V

Ensure Hyper-V is enabled on your Windows machine. You can do this through the Control Panel under "Turn Windows features on or off," selecting Hyper-V, and restarting your computer.

### Part 2: Creating Vagrantfile for Your Repository
**Vagrantfile:**

```
Vagrant.configure("2") do |config|
  # Set the Hyper-V box name
  config.vm.box = "generic/ubuntu2004"

  # Common provision for both VMs
  config.vm.provision "shell", inline: <<-SHELL
    sudo apt-get update -y
    sudo apt-get install -y iputils-ping avahi-daemon libnss-mdns unzip \
        openjdk-17-jdk-headless
  SHELL

  # Configurations specific to the database VM
  config.vm.define "db" do |db|
    # Use the Hyper-V provider
    db.vm.provider "hyperv" do |hyperv|
      # Specify Hyper-V-specific configurations
      hyperv.memory = 1024
      hyperv.cpus = 1
    end

    db.vm.hostname = "db"
    db.vm.network "private_network", ip: "192.168.56.11"

    # Port forwarding for H2 console and server
    db.vm.network "forwarded_port", guest: 8082, host: 8082
    db.vm.network "forwarded_port", guest: 9092, host: 9092

    # Download H2 database
    db.vm.provision "shell", inline: <<-SHELL
      wget https://repo1.maven.org/maven2/com/h2database/h2/1.4.200/h2-1.4.200.jar
    SHELL

    # Run H2 server process
    db.vm.provision "shell", :run => 'always', inline: <<-SHELL
      java -cp ./h2*.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -ifNotExists > ~/out.txt &
    SHELL
  end

  # Configurations specific to the webserver VM
  config.vm.define "web" do |web|
    # Use the Hyper-V provider
    web.vm.provider "hyperv" do |hyperv|
      # Specify Hyper-V-specific configurations
      hyperv.memory = 1024
      hyperv.cpus = 1
    end

    web.vm.hostname = "web"
    web.vm.network "private_network", ip: "192.168.56.10"

    # Port forwarding for Tomcat
    web.vm.network "forwarded_port", guest: 8080, host: 8080

    web.vm.provision "shell", inline: <<-SHELL, privileged: false
      sudo apt install -y tomcat9 tomcat9-admin

      # Change the following command to clone your own repository!
      git clone https://github.com/Beatrizsr96/devops-23-24-PSM-1231824.git
      cd ~/devops-23-24-PSM-1231824/CA2/Part2/react-and-spring-data-rest-basic
      chmod +wrx *
      ./gradlew clean build
      nohup ./gradlew bootrun > /home/vagrant/spring-boot-app.log 2>&1 &
    SHELL
  end
end
```

### Part 3: Running Virtual Machines

1. Accessing Applications
   Access the Spring Boot application by navigating to its IP address or hostname in a web browser. Similarly, access the H2 database console using its IP address and configured port.

### Part 4: Hyper-V Commands

1. Use `vagrant up --provider=hyperv` command to run the Vagrantfile using Hyper-V.
```
## Conclusion

By utilizing Hyper-V for virtualization, you can create and manage virtual machines efficiently for running applications and databases. Hyper-V offers a user-friendly interface, robust features, and excellent performance for virtualized environments.

