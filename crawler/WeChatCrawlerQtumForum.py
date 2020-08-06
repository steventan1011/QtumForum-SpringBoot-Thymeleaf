from requests_html import HTMLSession
import requests
import mysql.connector
import time, datetime
from lxml import etree

def download(query, url):
    data = {}
    session = HTMLSession()
    requests.packages.urllib3.disable_warnings()

    try:
        res = session.get(url)
        html = res.html
        # r = res.content
        # html_doc = str(r, 'utf-8')  # 此举旨在正确编码，避免乱码

        news_list = html.find('div.txt-box')
        for i in range(0, len(news_list)):
            data['category'] = query
            data['title'] = html.find('div.txt-box>h3>a')[i].text
            data['url'] = 'https://weixin.sogou.com' + html.find('div.txt-box>h3>a')[i].attrs['href']
            data['description'] = html.find('div.txt-box>p')[i].text
            data['source'] = html.find('div.txt-box>div>a')[i].text
            data['source_url'] = 'https://weixin.sogou.com' + html.find('div.txt-box>div>a')[i].attrs['href']
            temp = html.find('div.txt-box>div>span')[i].text
            time_stamp = int(str(temp).split('\'')[1])
            timeArray = time.localtime(time_stamp)
            otherStyleTime = time.strftime("%Y-%m-%d %H:%M:%S", timeArray)
            data['time'] = otherStyleTime
            data['img_url'] = html.find('div.img-box>a>img')[i].attrs['src'].split('&url=')[1]

            response = session.get(data['url'])
            print(response.status_code)
            print(response.url)
            # print(data)

            connectMYSQL(data)

    except (UnicodeDecodeError, IndexError, TimeoutError) as e:
        print(url)
        print(e)
    except (requests.exceptions.ConnectionError, requests.exceptions.MissingSchema) as e:
        print(url)
        print(e)


def connectMYSQL(data):
    cursor = conn.cursor(buffered=True)
    try:
        cursor.execute(
            "INSERT INTO sogou_news(`category`, `url`,`title`, `description`, `source`, `source_url`, `time`, `img_url`) VALUES (%s,%s,%s,%s,%s,%s,%s,%s)",
            [data['category'], data['url'], data['title'], data['description'], data['source'], data['source_url'], data['time'], data['img_url']])
        print('************** %s 数据保存成功 **************' % data['title'])
        conn.commit()
        cursor.close()
    except (mysql.connector.errors.IntegrityError, mysql.connector.errors.DataError) as e:
        print(e)


def whole_download(query, pages):
    for i in range(1, pages + 1):
        url = 'https://weixin.sogou.com/weixin?type=2&ie=utf8&query=' + query + 'page=' + str(i)
        download(query, url)





if __name__ == '__main__':
    conn = mysql.connector.connect(
        user='root',
        password='root',
        host='localhost',
        port='3306',
        database='liangzi'
    )
    query = '量子'
    pages = 20
    whole_download(query, pages)
    conn.close()
