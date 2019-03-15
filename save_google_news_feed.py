# importing the required modules
import csv
import requests
import xml.etree.ElementTree as ET
import urllib
import logging
import datetime
import os
import MySQLdb
#import pymysql as MySQLdb
# pymysql.install_as_MySQLdb()
import sys


class SaveNewsFromFeeds:
    def __init__(self):
        #database settings
        self.host = '127.0.0.1'
        self.port = '3306'
        self.user = 'root'
        # self.passwd = 'chalkdigital2017'
        self.passwd = 'Ch@1kDigit@1'
        self.databasename = 'ediyalabs'
        self.db = ''

        #news media settings
        self.newsAgency = "google_news"
        self.newsMediaFolder = "/images/"+self.newsAgency
        self.feedUrl = ["https://news.google.com/news/rss/?ned=in&gl=IN&hl=en-IN",
                        "https://news.google.com/news/rss/headlines/section/topic/NATION.en_in/India?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/headlines/section/topic/WORLD.en_in/World?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/headlines/section/topic/BUSINESS.en_in/Business?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/headlines/section/topic/TECHNOLOGY.en_in/Technology?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/headlines/section/topic/ENTERTAINMENT.en_in/Entertainment?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/headlines/section/topic/SPORTS.en_in/Sports?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/headlines/section/topic/HEALTH.en_in/Health?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/headlines/section/topic/SCIENCE.en_in/Science?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/local?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/local/section/geo/Bangalore,%20Karnataka,%20India/Bangalore,%20Karnataka,%20India%20%28Bengaluru,%20Karnataka,%20IN%29?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/local/section/geo/Kolkata,%20West%20Bengal,%20India/Kolkata,%20West%20Bengal,%20IN?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/local/section/geo/Mumbai,%20Maharashtra,%20India/Mumbai,%20Maharashtra,%20IN?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/local/section/geo/Chennai,%20Tamil%20Nadu,%20India/Chennai,%20Tamil%20Nadu,%20IN?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/local/section/geo/Uttarakhand,%20India/Uttarakhand,%20IN?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/local/section/geo/Uttar%20Pradesh,%20India/Uttar%20Pradesh,%20IN?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/local/section/geo/Punjab,%20India/Punjab,%20IN?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/local/section/geo/Himachal%20Pradesh,%20India/Himachal%20Pradesh,%20IN?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/local/section/geo/Hyderabad,%20Telangana,%20India/Hyderabad,%20Telangana,%20IN?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/local/section/geo/Nagaland,%20India/Nagaland,%20IN?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/sfy/section/q/Elon%20Musk/Elon%20Musk?gl=IN&ned=in&hl=en-IN",
                        "https://news.google.com/news/rss/sfy/section/q/Justin%20Bieber/Justin%20Bieber?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/sfy/section/q/Twitter/Twitter?ned=in&hl=en-IN&gl=IN",
                        "https://news.google.com/news/rss/sfy/section/q/Facebook/Facebook?hl=en-IN&gl=IN&ned=in",
                        "https://news.google.com/news/rss/sfy/section/q/WikiLeaks/WikiLeaks?hl=en-IN&gl=IN&ned=in",
                        "https://news.google.com/news/rss/sfy/section/q/Snapchat/Snapchat?hl=en-IN&gl=IN&ned=in",
                        "https://news.google.com/news/rss/sfy/section/q/Instagram/Instagram?hl=en-IN&gl=IN&ned=in",
                        "https://news.google.com/news/rss/sfy/section/q/Billboard%20charts/Billboard%20charts?hl=en-IN&gl=IN&ned=in",
                        "https://news.google.com/news/rss/sfy/section/q/United%20States%20of%20America/United%20States%20of%20America?hl=en-IN&gl=IN&ned=in",
                        "https://news.google.com/news/rss/sfy/section/q/Vladimir%20Putin/Vladimir%20Putin?hl=en-IN&gl=IN&ned=in"]

        # logging settings 
        self.logdir = '/root/kailash/logs/'
        self.logfileName = self.newsAgency+'_%s.log'
        self.logfileNameAndPath = self.logdir+self.logfileName

        self.dateInUTCYyyyMmDd = datetime.datetime.now().strftime('%Y%m%d')
        self.xmlfile = '/root/kailash/xmlx/newsfeed_%s.xml' % (self.dateInUTCYyyyMmDd)

    def parse_cmd_args(self, argv):

        # if (len(sys.argv) != 3):
        #     print 'PLZ RUN SCRIPT USING : python parse_news.python newsAgency=\'Hindustan_Times\' feedUrl=[\'http://www.hindustantimes.com/rss/topnews/rssfeed.xml\']'
        #     sys.exit()

        for arg in sys.argv:
            print 'arg [' + arg + ']'
            pv = arg.split('=')

            if (len(pv) == 2):
                if (pv[0] == 'newsAgency'):
                    self.newsAgency = pv[1]
                elif (pv[0] == 'feedUrl'):
                    self.feedUrl = pv[1]
        print "UPDATED -- newAgency = "+str(self.newsAgency)+" feedUrl = "+str(self.feedUrl)

    def log_init(self):
        todayInUTC = datetime.datetime.now()
        self.dateInUTCYyyyMmDd = todayInUTC.strftime('%Y%m%d')

        self.logfileNameAndPath = self.logfileName % self.dateInUTCYyyyMmDd
        fname = self.logdir + os.sep + self.logfileNameAndPath

        #clear old logs
        self.deleteFileContent(fname)

        logging.basicConfig(format='[%(asctime)s] %(message)s', filename=fname, filemode="a+", level=logging.DEBUG)

    def dbconnect(self):
        try :
            # Open database connection
            logging.info(
                'dbhost [' + self.host + '] port [' + self.port + '] dbuser [' + self.user + '] dbpass [' + self.passwd + '] dbname [' + self.databasename + ']')
            self.db = MySQLdb.connect(self.host, self.user, self.passwd, self.databasename, charset='utf8',
                                      use_unicode=True)
            logging.info('db connection successful..' + "\n");
        except MySQLdb.Error, e:
            logging.error("MYsql DB error %s",e)

    def dbdisconnect(self):
        self.db.close()
        logging.info('db disconnected.' + "\n");

    def loadRSS(self):
        try:
            for url in self.feedUrl:
                # url of rss feed
                logging.info("saving feed from ::"+url)
                # creating HTTP response object from given url
                resp = requests.get(url)

                # empty file
                self.deleteFileContent( self.xmlfile)

                # saving the xml file
                with open( self.xmlfile, 'wb') as f:
                    f.write(resp.content)
                self.parseXML( self.xmlfile)
        except Exception,e:
            logging.error("exception @loadRSS",e)

    def deleteFileContent(self,fName):
        with open(fName, "w"):
            pass

    def parseXML(self,xmlfile):
        # create element tree object
        tree = ET.parse(xmlfile)

        # get root element
        root = tree.getroot()

        # create empty list for news items
        newsitems = []

        # iterate news items
        for item in root.findall('./channel/item'):

            # empty news dictionary
            news = {}

            # iterate child elements of item
            for child in item:

                # special checking for namespace object content:media
                if child.tag == 'link':
                    # news['mediaLink'] = child.attrib['url']
                    news['hyperlink'] = child.text.encode('utf8')
                else:
                    news[child.tag] = child.text.encode('utf8')

            # append news dictionary to news items list
            newsitems.append(news)

        self.saveFeedToDb(newsitems)

    def savetoCSV(self,newsitems, filename):
        # specifying the fields for csv file
        fields = ['guid', 'title', 'pubDate', 'description', 'link', 'media','guid']

        # writing to csv file
        with open(filename, 'w') as csvfile:
            # creating a csv dict writer object
            writer = csv.DictWriter(csvfile, fieldnames=fields)

            # writing headers (field names)
            writer.writeheader()

            # writing data rows
            writer.writerows(newsitems)

    def saveFeedToDb(self,newsitems):
        try:
            logging.info("@saveFeedToDb --")
            # logging.info(newsitems)

            for item in newsitems:
                # Execute the SQL command
                cursor = self.db.cursor()
                pubDate = datetime.datetime.strptime(item['pubDate'],'%a, %d %b %Y %H:%M:%S %Z').strftime('%Y-%m-%d %H:%M:%S')
                createdAt = datetime.datetime.now();
                timeZone = item['pubDate'].split(" ")[5]
                if self.isNewsExist(item['title']):
                    logging.info("New already exist title is ["+item['title']+"]")
                    logging.info("ignoring it.")
                    continue
                cursor.execute(
                    "insert into google_news(title,hyperlink,category,pubDate,timezone,description,guid,createdAt) values (%s,%s,%s,%s,%s,%s,%s,now())",
                    [item['title'], item['hyperlink'], item['category'], pubDate,timeZone,item['description'],item['guid']])
                logging.info("record inserted."+item['title'], item['hyperlink'], item['category'], pubDate,timeZone,item['description'],item['guid'],createdAt)
                logging.info("news inserted successfully")

            cursor.close()
            self.db.commit()

        except MySQLdb.Error, e:
            logging.error("MYsql DB error %s",e)

    def isNewsExist (self,title):
        try:
            logging.info("@checkNewExist")
            cursor = self.db.cursor()
            cursor.execute("select id from google_news where lower(title) like %s",title.lower()+"%")
            logging.info('@isNewsExist : records present count is [' + str(cursor.rowcount) + ']')
            reccount = cursor.rowcount
            cursor.close()
            if reccount > 0:
                return True
            else:
                return False
        except MySQLdb.Error, e:
            logging.error("Mysql db exception %s",e)

    def saveNewsMediaToImageFolder(url, filePath):
        urllib.urlretrieve(url, filePath)

# Run main

savenewsObj = SaveNewsFromFeeds()

savenewsObj.parse_cmd_args(sys.argv)
savenewsObj.log_init()
savenewsObj.dbconnect()
savenewsObj.loadRSS()
savenewsObj.dbdisconnect()



