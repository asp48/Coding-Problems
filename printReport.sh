JAVA_FILES=`find . -iname "*.java"| wc -l`
echo "Java Files: ${JAVA_FILES}"
PYTHON_FILES=`find . -iname "*.py" | wc -l`
echo "Python Files: ${PYTHON_FILES}"

echo "Problems Solved under each topic:"
du -a | cut -d/ -f2 | sort | uniq -c | sort -nr | grep -v "\..*"

TODAY_COUNT=`find . -type f -iname "*.java" -mtime -1 -ctime -1 | wc -l` 
echo "Number of Problems solved today: ${TODAY_COUNT}"

WEEK_COUNT=`find . -type f -iname "*.java" -mtime -7 -ctime -7 | wc -l`
echo "Number of Problems Solved in last week: ${WEEK_COUNT}"

MONTH_COUNT=`find . -type f -iname "*.java" -mtime -30 -ctime -30 | wc -l`
echo "Number of Problems Solved in last month: ${MONTH_COUNT}"


