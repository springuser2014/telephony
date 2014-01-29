# C:\dev\Projects\telephony\telephony-core\src\main\resources\db\migration

# C:\dev\Projects\telephony\telephony-core\src\test\resources\db\data

# C:\dev\Projects\telephony\telephony-ws\src\main\resources\db\data

from os import listdir, remove
from os.path import isfile, join

paths = ['./../telephony-core/src/test/resources/db/data',
         './../telephony-core/src/main/resources/db/migration']

onlyfiles = []
fullpaths = []
file_to_version = {}
version_to_file = {}
file_to_fullfilepath = {}

for path in paths:
    for f in listdir(path):
        if isfile(join(path,f)):
            fullpath = join(path,f)
            fullpaths.append(fullpath)
            onlyfiles.append(f)
            file_to_fullfilepath[f] = fullpath

if isfile('./../migrations.sql'):
    remove('./../migrations.sql')

if isfile('./../migrations_file_list.sql'):
    remove('./../migrations_file_list.sql')

file_to_write = open ('./../migrations.sql' , 'w')
file_list_to_write = open ('./../migrations_file_list.sql' , 'w')

for filename in onlyfiles:
    splited = filename.split("__")
    version = splited[0]
    version = version.lstrip("Vv")
    version = version.replace("_", ".")
    version_to_file[float(version)] = filename
    file_to_version[filename] = float(version)

version_to_file_sorted_keys = sorted(version_to_file.iteritems(), key=lambda (x,y): float(x))


for k , v in version_to_file_sorted_keys:
    filename = version_to_file[k]
    file_to_write.write('--' + filename + '\n')
    file_list_to_write.write(filename + '\n')

    fullfilepath = file_to_fullfilepath[filename]
    f = open (fullfilepath, 'r')
    file_to_write.write(f.read() + '\n')
    f.close()

file_to_write.close()
file_list_to_write.close()
