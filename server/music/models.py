from __future__ import unicode_literals



from django.db import models

# Create your models here.
class Stock(models.Model):
    ticker=models.CharField(max_length=10)
    mp3song=models.FileField()
    open=models.FloatField()
    close=models.FloatField()
    volume=models.IntegerField()

    def __str__(self):
        return self.ticker


class Stock2(models.Model):
    ticker = models.CharField(max_length=10)
    open = models.FloatField()
    close = models.FloatField()
    volume = models.IntegerField()

    def __str__(self):
        return self.ticker

class Userdata(models.Model):
    email = models.CharField(max_length=100,primary_key=True)
    password = models.CharField(max_length=20)
    bandid=models.IntegerField

    def __str__(self):
        return self.email

class Band(models.Model):
    id=models.AutoField(primary_key=True)
    ticker = models.CharField(max_length=10)
    genre=models.CharField(max_length=10)
    history=models.CharField(max_length=500)
    formationyear=models.IntegerField()
    country=models.CharField(max_length=20)
    lineup = models.CharField(max_length=80)
    image = models.ImageField(upload_to="bandimages")

    def __str__(self):
        return self.ticker


class Album(models.Model):
    id=models.AutoField(primary_key=True)
    name=models.CharField(max_length=20)
    bandid=models.ForeignKey(Band,on_delete=models.CASCADE)
    releaseyear=models.IntegerField()
    songscount= models.IntegerField()
    image = models.ImageField()

    def __str__(self):
        return self.name

class Song(models.Model):
    id=models.AutoField(primary_key=True)
    name=models.CharField(max_length=20)
    albumid=models.ForeignKey(Album,on_delete=models.CASCADE)
    lyric=models.CharField(max_length=200)
    like=models.IntegerField()
    dislike=models.IntegerField()
    utubelink=models.CharField(max_length=100)


    def __str__(self):
        return self.name




