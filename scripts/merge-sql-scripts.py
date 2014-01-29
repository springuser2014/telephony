
# struktura bazy danych (na producje, test itd)
# C:\dev\Projects\telephony\telephony-core\src\main\resources\db\migration


# dane testowe
# C:\dev\Projects\telephony\telephony-core\src\test\resources\db\data


# inny katalog
# C:\dev\Projects\telephony\telephony-ws\src\main\resources\db\data


# algorytm
# 1. wczytac wszystkie nazwy plikow .sql
# 2. posortowac je wedlug nazwy
# 3. utworzyc nowy plik
# 4. wlozyc do niego zawartosc plikow sql wedlug kolejnosci rosnacej

from os import listdir, remove
from os.path import isfile, join

paths = ['C:/dev/Projects/telephony/telephony-core/src/test/resources/db/data',
         'C:/dev/Projects/telephony/telephony-core/src/main/resources/db/migration']

onlyfiles = []
fullpaths = []

for path in paths:
    for f in listdir(path):
        if isfile(join(path,f)):
            fullpaths.append(join(path,f))
            onlyfiles.append(f)

if isfile('C:/dev/Projects/telephony/migrations.sql'):
    remove('C:/dev/Projects/telephony/migrations.sql')

if isfile('C:/dev/Projects/telephony/migrations_file_list.sql'):
    remove('C:/dev/Projects/telephony/migrations_file_list.sql')

file_to_write = open ('C:/dev/Projects/telephony/migrations.sql' , 'w')
file_list_to_write = open ('C:/dev/Projects/telephony/migrations_file_list.sql' , 'w')

onlyfiles = sorted(onlyfiles)

_4th, _5th = onlyfiles.index('V5_1__Store_to_role_test_data.sql'), onlyfiles.index('V5__Added_store_roles_table.sql')
onlyfiles[_5th], onlyfiles[_4th] = onlyfiles[_4th], onlyfiles[_5th]

for file in onlyfiles:
    file_to_write.write('--' + file + '\n')
    file_list_to_write.write(file + '\n')

    for fullfilepath in fullpaths:
        if fullfilepath.find(file) > 0:
            f = open(fullfilepath, 'r')
            file_to_write.write(f.read() + '\n')
            f.close()

file_to_write.close()
file_list_to_write.close()
