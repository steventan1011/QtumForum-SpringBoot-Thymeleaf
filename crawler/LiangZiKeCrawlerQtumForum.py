from requests_html import HTMLSession
import requests
import mysql.connector
import time
from bs4 import BeautifulSoup
from fake_useragent import UserAgent
import random

global url_list
url_list = []
column2 = ["量子通信","量子计算", "量子通信", "量子物理", "量子理论", "量子模拟", "量子测量", "密码学"]
ua = UserAgent().random
headers = {'User-Agent': ua}


def download(url):

    data = {}
    session = HTMLSession()
    requests.packages.urllib3.disable_warnings()

    try:
        res = session.get(url, headers=headers)
        html = res.html
        # r = res.content
        # html_doc = str(r, 'utf-8')  # 此举旨在正确编码，避免乱码

        # 页面url
        data['url'] = url
        # 类别
        data['category'] = html.find('span.d-block')[0].text[1:-1]
        if '(专栏)' in data['category']:
            data['category'] = data['category'][:-4]
        # coloum2
        if data['category'] in column2:
            data['column2'] = column2.index(data['category']) + 1
        else:
            data['column2'] = 0
        # 标题
        data['title'] = html.find('h1.h3')[0].text

        # img图片
        content = html.find('div.post-content')[0].html
        bf = BeautifulSoup(content, "lxml")
        img_list = [str(i.attrs['data-src']) for i in bf.find_all("img")]
        data['img_url1'] = ""
        data['img_url2'] = ""
        data['img_url3'] = ""
        count = 1
        data['img_url'] = ""
        for img in img_list:
            data['img_url'] += img
            if count <= 3:
                data['img_url' + str(count)] = img
                count += 1
            data['img_url'] += " ; "

        # 内容
        for img in bf.find_all("img"):
            img.attrs["src"] = img.attrs["data-src"]
            img.attrs["width"] = '700px'
            img.attrs["height"] = ''
        data["content"] = str(bf.div)

        # data['content'] = html.find('div.post-content')[0].html

        # 简短描述
        data['description'] = html.find('div.post-content')[0].text
        if len(data['description']) > 200:
            data['description'] = data['description'][: 200]+"..."


        # 相关链接
        data['relate_url'] = ""
        relate_temp = html.find('a.list-title')
        for relate in relate_temp:
            data['relate_url'] += relate.attrs['href']
            data['relate_url'] += ' ; '

        connectMYSQL(data)

    except (UnicodeDecodeError, IndexError, TimeoutError) as e:
        print(url)
        print(e)
    except (requests.exceptions.ConnectionError, requests.exceptions.MissingSchema) as e:
        print(url)
        print(e)
    except (KeyError) as e:
        print(url)
        print(e)


def connectMYSQL(data):
    cursor = conn.cursor(buffered=True)
    try:
        cursor.execute(
            "INSERT INTO qtum_forum_news(`link`,`tag`, `title`, `html`, `description`, `relate_url`, `imageurl1`, `imageurl2`, `imageurl3`, `source`, `column2`) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",
            [data['url'], data['category'], data['title'], data['content'], data['description'], data['relate_url'], data['img_url1'], data['img_url2'], data['img_url3'], "量子客", data['column2']])
        print('************** %s 数据保存成功 **************' % data['title'])
        conn.commit()
        cursor.close()
    except (mysql.connector.errors.IntegrityError, mysql.connector.errors.DataError) as e:
        print(e)


def seedSpread(url):
    try:
        cursor = conn.cursor(buffered=True)
        cursor.execute(
            "SELECT `relate_url` FROM `qtum_forum_news` WHERE `link`='%s'" %url)
        spread = cursor.fetchone()
        conn.commit()
        cursor.close()
        spread = str(spread).strip('(').strip(')').strip(',')
        # print(spread)
        spreads = eval(spread).split(' ; ')
        # print(spreads)
        for i in spreads:
            if i not in url_list:
                url_list.append(i)
                download(i)
                time.sleep(1)
    except AttributeError as e:
        print(e)


def wholeDownload(url):
    # 第一条
    url_list.append(url)
    download(url)
    seedSpread(url)
    # 之后的每条
    num = 1
    while True:
        nextURL = url_list[num]
        download(nextURL)
        seedSpread(nextURL)
        num += 1


if __name__ == '__main__':
    conn = mysql.connector.connect(
        user='root',
        password='root',
        host='localhost',
        port='3306',
        database='qtumforum'
    )
    urlSeed = 'https://www.qtumist.com/post/3999'
    wholeDownload(urlSeed)
    conn.close()
