
require 'buildr/ivy_extension'

VERSION_NUMBER = ENV['revision'] || '1.0-SNAPSHOT'

repositories.remote << "http://www.ibiblio.org/maven2/"
repositories.release_to = 'sftp://joist.ws/var/joist.repo'
repositories.release_to[:permissions] = 0644

Buildr.settings.build['ivy']['home.dir'] = "#{ENV['HOME']}/.ivy2"
Java.java.lang.System.setProperty("revision", VERSION_NUMBER)

define "pageobjects" do
  project.version = VERSION_NUMBER
  project.group = 'org.bizo'
  ivy.compile_conf('default')
  package(:jar)
  package(:jar, :classifier => 'sources').clean.include :from => compile.sources

  # monkey patch the pom to always go out, courtesy of Alex
  file _("target/#{project.name}-#{project.version}.pom") => task('ivy:makepom')
  package(:jar).pom.tap do |pom|
    class << pom
      def needed?
        true
      end
    end
    _("target/#{project.name}-#{project.version}.pom").tap do |xml|
      pom.enhance [xml]
      pom.enhance { cp xml, pom.name }
    end
  end
end

