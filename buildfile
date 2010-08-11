
require 'buildr/ivy_extension'

VERSION_NUMBER = '1.0-SNAPSHOT'

repositories.remote << "http://www.ibiblio.org/maven2/"

i = Buildr.settings.build['ivy'] = {}
i['home.dir'] = "#{ENV['HOME']}/.ivy2"
i['settings.file'] = './ivysettings.xml'

define "page-objects" do
  project.version = VERSION_NUMBER
  project.group = 'org.bizo'
  ivy.compile_conf('default')
  package(:jar)
  package(:jar, :classifier => 'sources').clean.include :from => compile.sources


end

