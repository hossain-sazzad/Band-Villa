from rest_framework import serializers

from .models import Band
from .models import Album
from .models import Song
from .models import Stock
from .models import Stock2

class StockSerializer(serializers.ModelSerializer):
    class Meta:
        model=Stock
        fields = ('ticker','open','close','volume')

    class Meta:
        model = Stock2
        fields = ('ticker','open','close','volume')

class Bandserializer(serializers.ModelSerializer):

    class Meta:
        model = Band
        fields = '__all__'

class Bandserializer(serializers.ModelSerializer):

    class Meta:
        model = Band
        fields = '__all__'

class Albumserializer(serializers.ModelSerializer):

    class Meta:
        model = Album
        fields ='__all__'

class Songserializer(serializers.ModelSerializer):

    class Meta:
        model = Song
        fields ='__all__'

