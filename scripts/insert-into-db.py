import psycopg2
import psycopg2.extensions
import logging
import sys

# passed as cmd args
#db_to_remove = 'telephony-test'
#user = 'postgres'
#password = 'flyway'
db_to_remove = sys.argv.pop()
password = sys.argv.pop()
user = sys.argv.pop()
# 
main_db = 'postgres'

print 'Trying to connect using'
print 'db_to_remove = ' + db_to_remove 
print 'password = ' + password
print 'user  = ' + user 

try:
    conn = psycopg2.connect("dbname='" + main_db + "' user='" + user + "' password='" + password + "'")
except:
    print "I am unable to connect to the database."
conn.set_isolation_level(0)
cur = conn.cursor()

try:
    cur.execute("DROP DATABASE " + '"' + db_to_remove + '"' )
except Exception, exc:
    print "I cannot drop our test database!"
    print exc.message

try:
    cur.execute("CREATE DATABASE " + '"' + db_to_remove + '"' )
except Exception, exc:
    print "I cannot re-create test database!"
    print exc.message

conn.commit()
cur.close()
conn.close()

class LoggingCursor(psycopg2.extensions.cursor):
    def execute(self, sql, args=None):
        logger = logging.getLogger('sql_debug')
        logger.info(self.mogrify(sql, args))

        try:
            psycopg2.extensions.cursor.execute(self, sql, args)
        except Exception, exc:
            logger.error("%s: %s" % (exc.__class__.__name__, exc))
            raise

conn = psycopg2.connect("host='localhost' dbname='" + db_to_remove + "' user='" + user +"'  password='" + password + "'" )
cur = conn.cursor(cursor_factory=LoggingCursor)
cur.execute(open("C:/dev/Projects/telephony/migrations.sql", "r").read())
conn.commit()
cur.close()
conn.close()
