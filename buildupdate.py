from ftplib import FTP
import os

ftp = FTP("10.14.77.2", "anonymous", "")
ftp.login()
ftp.cwd("/")

file = open("buildnumbertemp.txt", 'r+')
ftp.retrlines('retr buildnumber.txt', file.write)

currentNumber = int(file.read()) + 1

file.seek(0)
file.write(str(currentNumber))
file.truncate()

ftp.storlines('stor buildnumber.txt', file)

file.close()
os.remove("buildnumbertemp.txt")
ftp.close()
