

from django.http import QueryDict
from django.shortcuts import render

from django.shortcuts import render
from django.shortcuts import get_object_or_404
from rest_framework.renderers import JSONRenderer
from rest_framework.views import APIView
from rest_framework.response import Response
from django.contrib.auth import authenticate,login
from .forms import Userform
from rest_framework import status
from .models import Stock
from .models import Stock2
from .models import Band
from .models import Album
from .models import Song
from .models import Userdata

from .serializers import StockSerializer
from .serializers import Bandserializer
from .serializers import Albumserializer
from .serializers import Songserializer

# Create your views here.
class Stocklist(APIView):
    def get(self,request):
        stocks=Stock.objects.all()
        serializers= StockSerializer(stocks,many=True)
        return Response(serializers.data)

    def post(self):
        pass

class Stocklist2(APIView):
    def get(self,request):
        stocks=Stock2.objects.all()
        serializers= StockSerializer(stocks,many=True)
        return Response(serializers.data)

    def post(self):
        pass

class Bandlist(APIView):

    def get(self,request):
        stocks=Band.objects.all()
        serializers= Bandserializer(stocks,many=True)
        return Response(serializers.data)

    def post(self,request):
        print("yesss")
        alldata=request.POST
        data = alldata.get("search", "0")
        stocks=Band.objects.filter(ticker__icontains=data)
        serializers= Bandserializer(stocks,many=True)


        return Response(serializers.data)

class upload_file:
    def post(self,request):
        form = request.POST
        form.save()
        return Response()

class Albumlist(APIView):
    def get(self,request):
        stocks=Album.objects.all()
        serializers= Albumserializer(stocks,many=True)
        return Response(serializers.data)

    def post(self,request):
        print("yesss")
        alldata = request.POST
        name = alldata.get("name", "0")
        ry = alldata.get("ry", "0")
        sc = alldata.get("sc", "0")
        bandid = alldata.get("id", "0")
        print (bandid)
        print ("bandid")
        entry=Album(name=name,releaseyear=ry,songscount=sc,bandid=Band.objects.get(id=bandid))
        entry.save()
        return Response("hoise to")



class Songlist(APIView):
    def get(self,request):
        stocks=Song.objects.all()
        serializers= Songserializer(stocks,many=True)
        return Response(serializers.data)

    def post(self,request):
        print("yesss")
        alldata = request.POST
        data = alldata.get("search", "0")
        stocks = Song.objects.filter(name__icontains=data)
        serializers = Songserializer(stocks, many=True)

        return Response(serializers.data)

class bandAlbumlist(APIView):
    def get(self,request,band_id):
        stocks = Album.objects.filter(bandid=band_id)
        serializers= Albumserializer(stocks,many=True)
        return Response(serializers.data)

    def post(self):
        pass




class QueryBandlist(APIView):

    def get(self,request,search):
        print ("search")
        print(search)
        stocks = Band.objects.filter(ticker__istartswith=search)
        serializers = Bandserializer(stocks, many=True)
        return Response(serializers.data)

    def post(self,request):
        pass




class QueryAlbumlist(APIView):
    def get(self,request,search):
        print ("search")
        print(search)
        stocks = Album.objects.filter(name__istartswith=search)
        serializers = Albumserializer(stocks, many=True)
        return Response(serializers.data)

    def post(self,request):
        pass


class QuerySonglist(APIView):
    def get(self,request,search):
        print ("search")
        print(search)
        stocks = Song.objects.filter(name__istartswith=search)
        serializers = Songserializer(stocks, many=True)
        return Response(serializers.data)

    def post(self,request):
        pass


class albumSonglist(APIView):
    def get(self,request,album_id):
        stocks = Song.objects.filter(albumid=album_id,)
        serializers= Songserializer(stocks,many=True)
        return Response(serializers.data)

    def post(self):
        pass


class registerview(APIView):
    form_class=Userform
    def get(self,request):
        pass
    def post(self,request):
        alldata = request.POST
        email = alldata.get("email", "0")
        password = alldata.get("password", "0")
        print("email")
        print(email)
        print("pass")
        print(password)

        try:
            entry=Userdata.objects.get(email=email)
            return Response("email  exists!!!")
        except Userdata.DoesNotExist:
            user=Userdata(email=email,password=password)
            user.save()
            return Response("valid")






class loginview(APIView):
    form_class=Userform
    def get(self,request):
        pass
    def post(self,request):
        alldata = request.POST
        email = alldata.get("email", "0")
        password = alldata.get("password", "0")
        print("email")
        print(email)
        print("pass")
        print(password)
        try:
            entry=Userdata.objects.get(email=email)
            if password==entry.password:
                return Response("successful")
            else:
                return Response("invalid password")



        except Userdata.DoesNotExist:
            return Response("user does mot exists!!!")























