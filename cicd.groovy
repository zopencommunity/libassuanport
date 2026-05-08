node('linux')
{
  stage ('Poll') {
    checkout([
        $class: 'GitSCM',
        branches: [[name: '*/main']],
        doGenerateSubmoduleConfigurations: false,
        extensions: [],
        userRemoteConfigs: [[url: 'https://github.com/zopencommunity/libassuanport.git']]])
  }
  
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/libassuanport.git'), string(name: 'PORT_DESCRIPTION', value: 'This is a library implementing the so-called Assuan protocol. This protocol is used for IPC between most newer GnuPG components. Both, server and client side functions are provided.' )]
  }
}
