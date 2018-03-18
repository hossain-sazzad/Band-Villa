from django.contrib import admin

# Register your models here.
from .models import Stock
from .models import Stock2
from .models import Band
from .models import Album
from .models import Song
from .models import Userdata

admin.site.register(Stock)
admin.site.register(Stock2)
admin.site.register(Band)
admin.site.register(Album)
admin.site.register(Song)
admin.site.register(Userdata)