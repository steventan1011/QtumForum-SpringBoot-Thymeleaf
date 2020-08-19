import re
from lxml import etree
import os

def getFileFromThisRootDir(dir,ext = None):
  allfiles = []
  needExtFilter = (ext != None)
  for root,dirs,files in os.walk(dir):
    for filespath in files:
      filepath = os.path.join(root, filespath)
      extension = os.path.splitext(filepath)[1][1:]
      if needExtFilter and extension in ext:
        allfiles.append(filepath)
      elif not needExtFilter:
        allfiles.append(filepath)
  return allfiles



def regex(file):

    html_file = open(file, 'r', encoding='utf-8')
    html = html_file.read()

    pattern1 = '( href=\")(.+?)\"'
    pattern2 = '(th:href=\")(.+?)\"'
    pattern3 = '( src=\")(.+?)\"'
    pattern4 = '(th:src=\")(.+?)\"'


    html = re.sub(pattern1, lambda m: ' th:href=\"' + '@{' + m.group(2) + '}"', html)
    html = re.sub(pattern2, lambda m: m.group(1) + '@{' + m.group(2) + '}"', html)
    html = re.sub(pattern3, lambda m: ' th:src=\"' + '@{' + m.group(2) + '}"', html)
    html = re.sub(pattern4, lambda m: m.group(1) + '@{' + m.group(2) + '}"', html)

    with open(file, 'w', encoding='utf-8') as file_object:
        file_object.write(html)

    return html


if __name__ == '__main__':
    all_files = getFileFromThisRootDir("C:\\users\\steve\\desktop\\QtumForum\\src\\main\\resources\\templates", "html")
    for file in all_files:
        result = regex(file)
        print(result)