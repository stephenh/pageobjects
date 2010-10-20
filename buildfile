
require 'buildr/ivy_extension'

VERSION_NUMBER = ENV['revision'] || '1.0-SNAPSHOT'

repositories.remote << "http://www.ibiblio.org/maven2/"
repositories.release_to = 'sftp://joist.ws/var/joist.repo'
repositories.release_to[:permissions] = 0644

# be nice if this was the default...
Buildr.settings.build['ivy'] = {}
Buildr.settings.build['ivy']['home.dir'] = "#{ENV['HOME']}/.ivy2"
Buildr.settings.build['ivy']['settings.file'] = 'ivysettings.xml'
# to resolve the ${revision} in the ivy.xml
Java.java.lang.System.setProperty("revision", VERSION_NUMBER)

define "pageobjects" do
  project.version = VERSION_NUMBER
  project.group = 'com.bizo'
  ivy.compile_conf('compile')

  package
  package_with_sources

  package(:jar).pom.tap do |pom|
    pom.enhance [task('ivy:makepom')]
    pom.from 'target/pom.xml'
  end
end

