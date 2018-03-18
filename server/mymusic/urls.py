"""mywebsite URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.9/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Add an import:  from blog import urls as blog_urls
    2. Import the include() function: from django.conf.urls import url, include
    3. Add a URL to urlpatterns:  url(r'^blog/', include(blog_urls))
"""
from django.conf.urls import url
from django.contrib import admin
from rest_framework.urlpatterns import format_suffix_patterns
from music import views
from django.conf import settings
from django.conf.urls.static import static

urlpatterns = [
                  url(r'^admin/', admin.site.urls),
                  url(r'^stocks/', views.Stocklist.as_view()),
                  url(r'^stocks2/', views.Stocklist2.as_view()),
                  url(r'^band/', views.Bandlist.as_view()),
                  url(r'^album/(?P<band_id>[0-9]+)/$', views.bandAlbumlist.as_view()),
                  url(r'^albums/(?P<search>[a-zA-Z]+)/$', views.QueryAlbumlist.as_view()),
                  url(r'^song/(?P<album_id>[0-9]+)/$', views.albumSonglist.as_view()),
                  url(r'^songs/(?P<search>[a-zA-Z]+)/$', views.QuerySonglist.as_view()),
                  url(r'^bands/(?P<search>[a-zA-Z]+)/$', views.QueryBandlist.as_view()),

                  url(r'^album/', views.Albumlist.as_view()),
                  url(r'^song/', views.Songlist.as_view()),
                  url(r'^register/', views.registerview.as_view()),
                  url(r'^login/', views.loginview.as_view()),

              ] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)


urlpatterns=format_suffix_patterns(urlpatterns)