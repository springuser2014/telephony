import psycopg2
import psycopg2.extensions
import logging

db_to_remove = 'telephony-test'
main_db = 'postgres'

try:
    conn = psycopg2.connect("dbname='" + main_db + "' user='postgres' password='flyway'")
except:
    print "I am unable to connect to the database."
conn.set_isolation_level(0)
cur = conn.cursor()

try:
    cur.execute("DROP DATABASE " + db_to_remove )
except:
    print "I can't drop our test database!"

try:
    cur.execute("CREATE DATABASE " + db_to_remove)
except:
    print "I can't drop our test database!"

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

conn = psycopg2.connect("host='localhost' dbname='" + db_to_remove + "' user='postgres'  password='flyway'" )
cur = conn.cursor(cursor_factory=LoggingCursor)
cur.execute(open("C:/dev/Projects/telephony/migrations.sql", "r").read())
conn.commit()
cur.close()
conn.close()