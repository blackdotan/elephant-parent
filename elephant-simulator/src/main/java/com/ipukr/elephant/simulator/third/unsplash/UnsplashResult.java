package com.ipukr.elephant.simulator.third.unsplash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/5/1.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnsplashResult {


    /**
     * total : 2176
     * total_pages : 726
     * results : [{"id":202618,"title":"Fruits & Vegetables","description":null,"published_at":"2016-04-24T14:02:13-04:00","updated_at":"2017-11-26T02:35:46-05:00","curated":false,"featured":true,"total_photos":93,"private":false,"share_key":"8ad46b54efc64f02a6d730ab99b09ba2","tags":[{"title":"vegetable"},{"title":"fruit"},{"title":"food"},{"title":"healthy"},{"title":"red"},{"title":"fresh"}],"cover_photo":{"id":"QY300DHjPaQ","created_at":"2016-08-18T08:45:23-04:00","updated_at":"2017-11-01T04:18:55-04:00","width":4272,"height":2848,"color":"#D7DCE3","description":"Glass bowl of fresh red strawberries","categories":[],"urls":{"raw":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&s=a1830a53787fe5d0bdd8c1716d42de93","full":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=275a4274f5bb3ada7ef8efef72a4ec41","regular":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=36121aa29531e73d438242130aaad341","small":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=2212f2146d236f7de493e34f1ae1088a","thumb":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=9bac5f792bbe4962e6911defa35ca379"},"links":{"self":"https://api.unsplash.com/photos/QY300DHjPaQ","html":"https://unsplash.com/photos/QY300DHjPaQ","download":"https://unsplash.com/photos/QY300DHjPaQ/download","download_location":"https://api.unsplash.com/photos/QY300DHjPaQ/download"},"liked_by_user":false,"sponsored":false,"likes":394,"user":{"id":"oY46cAl6JV4","updated_at":"2018-04-27T00:27:35-04:00","username":"robertina","name":"Roberta Sorge","first_name":"Roberta","last_name":"Sorge","twitter_username":null,"portfolio_url":"https://www.instagram.com/robertasorge_/","bio":null,"location":null,"links":{"self":"https://api.unsplash.com/users/robertina","html":"https://unsplash.com/@robertina","photos":"https://api.unsplash.com/users/robertina/photos","likes":"https://api.unsplash.com/users/robertina/likes","portfolio":"https://api.unsplash.com/users/robertina/portfolio","following":"https://api.unsplash.com/users/robertina/following","followers":"https://api.unsplash.com/users/robertina/followers"},"profile_image":{"small":"https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=79316fdcfc60f8ee549009def5f69c7f","medium":"https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=8fa60bd04c76ae09079576d94fcd9e5f","large":"https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=5ac46f51cddf728ec2d56494f80f3ab3"},"total_collections":0,"instagram_username":"robertasorge_","total_likes":22,"total_photos":36}},"preview_photos":[{"id":125549,"urls":{"raw":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&s=a1830a53787fe5d0bdd8c1716d42de93","full":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=275a4274f5bb3ada7ef8efef72a4ec41","regular":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=36121aa29531e73d438242130aaad341","small":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=2212f2146d236f7de493e34f1ae1088a","thumb":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=9bac5f792bbe4962e6911defa35ca379"}},{"id":124160,"urls":{"raw":"https://images.unsplash.com/photo-1471248026681-35a45d5530a3?ixlib=rb-0.3.5&s=69ae2f9dd24d3516e6fd3757388ea573","full":"https://images.unsplash.com/photo-1471248026681-35a45d5530a3?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=f2f02c85fd1f878398484e778f7cacf9","regular":"https://images.unsplash.com/photo-1471248026681-35a45d5530a3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=55d6684b790c8088a2b766f65a022636","small":"https://images.unsplash.com/photo-1471248026681-35a45d5530a3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=3e83ad9bcc54205ab9682f278f1d2b2c","thumb":"https://images.unsplash.com/photo-1471248026681-35a45d5530a3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=e3b6320d2c4f07c4aff6577ba13ee456"}},{"id":123631,"urls":{"raw":"https://images.unsplash.com/photo-1471113082645-fde63c139087?ixlib=rb-0.3.5&s=1b63b3b1b7d05fd00272cd81f66981c8","full":"https://images.unsplash.com/photo-1471113082645-fde63c139087?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=3fdcb51262f890c1753dda5f46611e0b","regular":"https://images.unsplash.com/photo-1471113082645-fde63c139087?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=55d50d9aa9d47f5039eac8838ab0e781","small":"https://images.unsplash.com/photo-1471113082645-fde63c139087?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=e8618760539f29dc65823dfcea0ea057","thumb":"https://images.unsplash.com/photo-1471113082645-fde63c139087?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=7e5f0e913d0355d0a07da56c2f7f2e99"}},{"id":123624,"urls":{"raw":"https://images.unsplash.com/photo-1471111471478-429bc38f2da4?ixlib=rb-0.3.5&s=65cf26e71f7c07910b1c8e1d0d16df6d","full":"https://images.unsplash.com/photo-1471111471478-429bc38f2da4?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=d06056cde3fdd9d2028b2691d56cf890","regular":"https://images.unsplash.com/photo-1471111471478-429bc38f2da4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=7f04625bd976373b8700023456343156","small":"https://images.unsplash.com/photo-1471111471478-429bc38f2da4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=b0be3f8580c5c90772bae21a925b25c3","thumb":"https://images.unsplash.com/photo-1471111471478-429bc38f2da4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=7fa2c27fd2107a9063692a353fd3905b"}}],"user":{"id":"x5oeUQw83Gk","updated_at":"2018-04-29T16:18:05-04:00","username":"heatherd","name":"Heather Dou","first_name":"Heather","last_name":"Dou","twitter_username":null,"portfolio_url":null,"bio":null,"location":null,"links":{"self":"https://api.unsplash.com/users/heatherd","html":"https://unsplash.com/@heatherd","photos":"https://api.unsplash.com/users/heatherd/photos","likes":"https://api.unsplash.com/users/heatherd/likes","portfolio":"https://api.unsplash.com/users/heatherd/portfolio","following":"https://api.unsplash.com/users/heatherd/following","followers":"https://api.unsplash.com/users/heatherd/followers"},"profile_image":{"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"},"total_collections":23,"instagram_username":null,"total_likes":128,"total_photos":0},"links":{"self":"https://api.unsplash.com/collections/202618","html":"https://unsplash.com/collections/202618/fruits-vegetables","photos":"https://api.unsplash.com/collections/202618/photos","related":"https://api.unsplash.com/collections/202618/related"}},{"id":216124,"title":"Fruits and Veggies","description":"Fruits and Vegetables to use on Fruta à Porta","published_at":"2016-05-09T13:46:23-04:00","updated_at":"2018-02-05T13:26:17-05:00","curated":false,"featured":true,"total_photos":104,"private":false,"share_key":"723a01016b05f9a382b0f86ccf087876","tags":[{"title":"veggy"},{"title":"fruit"},{"title":"food"},{"title":"vegetable"},{"title":"green"},{"title":"fresh"}],"cover_photo":{"id":"uiPaZkzjv64","created_at":"2016-12-17T17:16:06-05:00","updated_at":"2017-10-31T23:57:41-04:00","width":6000,"height":4000,"color":"#F9AE72","description":null,"categories":[],"urls":{"raw":"https://images.unsplash.com/photo-1482012792084-a0c3725f289f?ixlib=rb-0.3.5&s=ada623e3ef1b9770355e0276fb1859ae","full":"https://images.unsplash.com/photo-1482012792084-a0c3725f289f?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=a5d6a95368c580e720cbcf328f24ac32","regular":"https://images.unsplash.com/photo-1482012792084-a0c3725f289f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=7288d6dd89acc96b247d1f701ff317dc","small":"https://images.unsplash.com/photo-1482012792084-a0c3725f289f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=98363cc7f87e1b8e9d4b76a777d8a6ac","thumb":"https://images.unsplash.com/photo-1482012792084-a0c3725f289f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=cc58ed7fe58252e699f81c84ad028274"},"links":{"self":"https://api.unsplash.com/photos/uiPaZkzjv64","html":"https://unsplash.com/photos/uiPaZkzjv64","download":"https://unsplash.com/photos/uiPaZkzjv64/download","download_location":"https://api.unsplash.com/photos/uiPaZkzjv64/download"},"liked_by_user":false,"sponsored":false,"likes":366,"user":{"id":"wX1U-hAMGD8","updated_at":"2018-04-28T15:43:51-04:00","username":"jonathanpielmayer","name":"Jonathan Pielmayer","first_name":"Jonathan","last_name":"Pielmayer","twitter_username":null,"portfolio_url":"http://www.lockervomhocker.de","bio":null,"location":null,"links":{"self":"https://api.unsplash.com/users/jonathanpielmayer","html":"https://unsplash.com/@jonathanpielmayer","photos":"https://api.unsplash.com/users/jonathanpielmayer/photos","likes":"https://api.unsplash.com/users/jonathanpielmayer/likes","portfolio":"https://api.unsplash.com/users/jonathanpielmayer/portfolio","following":"https://api.unsplash.com/users/jonathanpielmayer/following","followers":"https://api.unsplash.com/users/jonathanpielmayer/followers"},"profile_image":{"small":"https://images.unsplash.com/profile-1445533135038-421b6ddf7c69?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=c0b0d09ee488f5c8112b9a86eda5121a","medium":"https://images.unsplash.com/profile-1445533135038-421b6ddf7c69?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=6019c336f0893220aae5bd360e413bd5","large":"https://images.unsplash.com/profile-1445533135038-421b6ddf7c69?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=748a04091082b307278ca09e8faad028"},"total_collections":0,"instagram_username":null,"total_likes":54,"total_photos":33}},"preview_photos":[{"id":176664,"urls":{"raw":"https://images.unsplash.com/photo-1482012792084-a0c3725f289f?ixlib=rb-0.3.5&s=ada623e3ef1b9770355e0276fb1859ae","full":"https://images.unsplash.com/photo-1482012792084-a0c3725f289f?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=a5d6a95368c580e720cbcf328f24ac32","regular":"https://images.unsplash.com/photo-1482012792084-a0c3725f289f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=7288d6dd89acc96b247d1f701ff317dc","small":"https://images.unsplash.com/photo-1482012792084-a0c3725f289f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=98363cc7f87e1b8e9d4b76a777d8a6ac","thumb":"https://images.unsplash.com/photo-1482012792084-a0c3725f289f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=cc58ed7fe58252e699f81c84ad028274"}},{"id":341998,"urls":{"raw":"https://images.unsplash.com/photo-1502825751399-28baa9b81efe?ixlib=rb-0.3.5&s=1aeb02558601f68d73ec7df768b60643","full":"https://images.unsplash.com/photo-1502825751399-28baa9b81efe?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=d5ccd3dc262dbb7419bf9e77f2959384","regular":"https://images.unsplash.com/photo-1502825751399-28baa9b81efe?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=ec9a39bdba6276a28fb3c8d586bbff99","small":"https://images.unsplash.com/photo-1502825751399-28baa9b81efe?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=916eb4178bf2d32d1b42a24130be9ead","thumb":"https://images.unsplash.com/photo-1502825751399-28baa9b81efe?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=1c7147889131deb8ab49626189059d2c"}},{"id":97622,"urls":{"raw":"https://images.unsplash.com/photo-1464454709131-ffd692591ee5?ixlib=rb-0.3.5&s=40cf9bc3de673dedab647e47727ded96","full":"https://images.unsplash.com/photo-1464454709131-ffd692591ee5?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=fd66eee24ca849aa7b93e768de8a6057","regular":"https://images.unsplash.com/photo-1464454709131-ffd692591ee5?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=d15d8477f01a0de58f4713e06eed9798","small":"https://images.unsplash.com/photo-1464454709131-ffd692591ee5?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=b5f6029e88bd1ebe70698177fb2af15b","thumb":"https://images.unsplash.com/photo-1464454709131-ffd692591ee5?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=5e53be0dcffde3e7edaf44e42ddd13a9"}},{"id":114470,"urls":{"raw":"https://images.unsplash.com/photo-1469547371150-47620ed0c5ea?ixlib=rb-0.3.5&s=08917acf7cad8135fbacd07f30289f10","full":"https://images.unsplash.com/photo-1469547371150-47620ed0c5ea?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=f6ee2784fb7889cb9c0292f4ae062f71","regular":"https://images.unsplash.com/photo-1469547371150-47620ed0c5ea?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=b447264f10fdf312660a9b6035660798","small":"https://images.unsplash.com/photo-1469547371150-47620ed0c5ea?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=620b8197a8c2d20060a2aa68a4dbc88d","thumb":"https://images.unsplash.com/photo-1469547371150-47620ed0c5ea?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=194eb41af102a28a7e7f476c12482bea"}}],"user":{"id":"i-VyOrn18ts","updated_at":"2018-04-22T14:12:20-04:00","username":"joanareosa","name":"Joana Areosa","first_name":"Joana","last_name":"Areosa","twitter_username":null,"portfolio_url":null,"bio":null,"location":null,"links":{"self":"https://api.unsplash.com/users/joanareosa","html":"https://unsplash.com/@joanareosa","photos":"https://api.unsplash.com/users/joanareosa/photos","likes":"https://api.unsplash.com/users/joanareosa/likes","portfolio":"https://api.unsplash.com/users/joanareosa/portfolio","following":"https://api.unsplash.com/users/joanareosa/following","followers":"https://api.unsplash.com/users/joanareosa/followers"},"profile_image":{"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"},"total_collections":8,"instagram_username":null,"total_likes":0,"total_photos":0},"links":{"self":"https://api.unsplash.com/collections/216124","html":"https://unsplash.com/collections/216124/fruits-and-veggies","photos":"https://api.unsplash.com/collections/216124/photos","related":"https://api.unsplash.com/collections/216124/related"}},{"id":1078131,"title":"fruit","description":null,"published_at":"2017-08-08T22:00:24-04:00","updated_at":"2018-04-08T06:45:46-04:00","curated":false,"featured":false,"total_photos":176,"private":false,"share_key":"e5d82c3a3e4632d241133e0873a54292","tags":[{"title":"fruit"},{"title":"food"},{"title":"green"},{"title":"fresh"},{"title":"organic"},{"title":"leaf"}],"cover_photo":{"id":"09T-J386jW4","created_at":"2018-02-21T09:19:22-05:00","updated_at":"2018-02-22T07:41:26-05:00","width":3000,"height":2000,"color":"#F5CAC3","description":null,"categories":[],"urls":{"raw":"https://images.unsplash.com/photo-1519222712135-ebfcf5951536?ixlib=rb-0.3.5&s=64453a58e25a3a45db94042fe55abd7c","full":"https://images.unsplash.com/photo-1519222712135-ebfcf5951536?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=e2c3d770cf59f4ca0693b0dcb4e21ee7","regular":"https://images.unsplash.com/photo-1519222712135-ebfcf5951536?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=d58c06ea3913cfa13e296f502abf50b7","small":"https://images.unsplash.com/photo-1519222712135-ebfcf5951536?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=ebf694ba197392fde8c325979dd6f98c","thumb":"https://images.unsplash.com/photo-1519222712135-ebfcf5951536?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=bc00fbe9aa9a71de8ca45d58d61ae190"},"links":{"self":"https://api.unsplash.com/photos/09T-J386jW4","html":"https://unsplash.com/photos/09T-J386jW4","download":"https://unsplash.com/photos/09T-J386jW4/download","download_location":"https://api.unsplash.com/photos/09T-J386jW4/download"},"liked_by_user":false,"sponsored":false,"likes":10,"user":{"id":"_y9UEiIhlXo","updated_at":"2018-04-27T21:09:30-04:00","username":"marcosecchi","name":"Marco Secchi","first_name":"Marco","last_name":"Secchi","twitter_username":"marcosecchi","portfolio_url":"https://www.msecchi.com","bio":"Photojournalist / Photographer / Storyteller. \r\nI create my work with the help of a camera and the brand is irrelevant!\r\nFounder of Awakening Photo Agency.","location":"Ljubljana","links":{"self":"https://api.unsplash.com/users/marcosecchi","html":"https://unsplash.com/@marcosecchi","photos":"https://api.unsplash.com/users/marcosecchi/photos","likes":"https://api.unsplash.com/users/marcosecchi/likes","portfolio":"https://api.unsplash.com/users/marcosecchi/portfolio","following":"https://api.unsplash.com/users/marcosecchi/following","followers":"https://api.unsplash.com/users/marcosecchi/followers"},"profile_image":{"small":"https://images.unsplash.com/profile-fb-1516975404-b95a04916f51.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=e992bbccac1c3d9362cf44d79992c35c","medium":"https://images.unsplash.com/profile-fb-1516975404-b95a04916f51.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=d90aec9d406126c43e8dd213440b4379","large":"https://images.unsplash.com/profile-fb-1516975404-b95a04916f51.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=6fff76443f8334cb2675fb00243b0786"},"total_collections":1,"instagram_username":"msecchi","total_likes":2,"total_photos":22}},"preview_photos":[{"id":568578,"urls":{"raw":"https://images.unsplash.com/photo-1519222712135-ebfcf5951536?ixlib=rb-0.3.5&s=64453a58e25a3a45db94042fe55abd7c","full":"https://images.unsplash.com/photo-1519222712135-ebfcf5951536?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=e2c3d770cf59f4ca0693b0dcb4e21ee7","regular":"https://images.unsplash.com/photo-1519222712135-ebfcf5951536?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=d58c06ea3913cfa13e296f502abf50b7","small":"https://images.unsplash.com/photo-1519222712135-ebfcf5951536?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=ebf694ba197392fde8c325979dd6f98c","thumb":"https://images.unsplash.com/photo-1519222712135-ebfcf5951536?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=bc00fbe9aa9a71de8ca45d58d61ae190"}},{"id":568581,"urls":{"raw":"https://images.unsplash.com/photo-1519222774993-4e0045b7757b?ixlib=rb-0.3.5&s=9540657a930c8a739c730af976270010","full":"https://images.unsplash.com/photo-1519222774993-4e0045b7757b?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=d3159a1f22b9d2cff2f10577aa25f546","regular":"https://images.unsplash.com/photo-1519222774993-4e0045b7757b?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=a6d9a59e27c3f782e84dc8697bd47418","small":"https://images.unsplash.com/photo-1519222774993-4e0045b7757b?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=5bd02a8dfa9eae5c0628ff83cce04b12","thumb":"https://images.unsplash.com/photo-1519222774993-4e0045b7757b?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=50ac1144ba49e39695cdbc0d50b7a647"}},{"id":43247,"urls":{"raw":"https://images.unsplash.com/photo-1446655398454-c644528513f5?ixlib=rb-0.3.5&s=2a6a4bcccdf8960cd12597abcd100c8c","full":"https://images.unsplash.com/photo-1446655398454-c644528513f5?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=02704373e82dba1d4021c37fea317c4c","regular":"https://images.unsplash.com/photo-1446655398454-c644528513f5?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=9392dc90ccdbb33330a20b8628571204","small":"https://images.unsplash.com/photo-1446655398454-c644528513f5?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=c8dc5fefca94bf686eaf8a954fbe19bc","thumb":"https://images.unsplash.com/photo-1446655398454-c644528513f5?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=6b077c6555d8bc610e0da1ef5d138e88"}},{"id":51232,"urls":{"raw":"https://images.unsplash.com/photo-1449843970446-467da965e181?ixlib=rb-0.3.5&s=712aba8b024a342a8d162b6bdec86d38","full":"https://images.unsplash.com/photo-1449843970446-467da965e181?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=e43d68576b198ba548de1f7c3c4ea096","regular":"https://images.unsplash.com/photo-1449843970446-467da965e181?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=9e78c31c5526c9ad5040509fb66390b2","small":"https://images.unsplash.com/photo-1449843970446-467da965e181?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=43f561c57b2a73d6303e7ced72d3b364","thumb":"https://images.unsplash.com/photo-1449843970446-467da965e181?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=4de1b301f16fa37049c540dfcb7aec9e"}}],"user":{"id":"Kw7gj_YQ18Q","updated_at":"2018-04-13T12:24:09-04:00","username":"xiaowanzi","name":"liu tt","first_name":"liu","last_name":"tt","twitter_username":null,"portfolio_url":null,"bio":null,"location":null,"links":{"self":"https://api.unsplash.com/users/xiaowanzi","html":"https://unsplash.com/@xiaowanzi","photos":"https://api.unsplash.com/users/xiaowanzi/photos","likes":"https://api.unsplash.com/users/xiaowanzi/likes","portfolio":"https://api.unsplash.com/users/xiaowanzi/portfolio","following":"https://api.unsplash.com/users/xiaowanzi/following","followers":"https://api.unsplash.com/users/xiaowanzi/followers"},"profile_image":{"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"},"total_collections":33,"instagram_username":null,"total_likes":6054,"total_photos":0},"links":{"self":"https://api.unsplash.com/collections/1078131","html":"https://unsplash.com/collections/1078131/fruit","photos":"https://api.unsplash.com/collections/1078131/photos","related":"https://api.unsplash.com/collections/1078131/related"}}]
     */

    private int total;
    private int total_pages;
    private List<ResultsDTO> results;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<ResultsDTO> getResults() {
        return results;
    }

    public void setResults(List<ResultsDTO> results) {
        this.results = results;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResultsDTO {
        /**
         * id : 202618
         * title : Fruits & Vegetables
         * description : null
         * published_at : 2016-04-24T14:02:13-04:00
         * updated_at : 2017-11-26T02:35:46-05:00
         * curated : false
         * featured : true
         * total_photos : 93
         * private : false
         * share_key : 8ad46b54efc64f02a6d730ab99b09ba2
         * tags : [{"title":"vegetable"},{"title":"fruit"},{"title":"food"},{"title":"healthy"},{"title":"red"},{"title":"fresh"}]
         * cover_photo : {"id":"QY300DHjPaQ","created_at":"2016-08-18T08:45:23-04:00","updated_at":"2017-11-01T04:18:55-04:00","width":4272,"height":2848,"color":"#D7DCE3","description":"Glass bowl of fresh red strawberries","categories":[],"urls":{"raw":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&s=a1830a53787fe5d0bdd8c1716d42de93","full":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=275a4274f5bb3ada7ef8efef72a4ec41","regular":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=36121aa29531e73d438242130aaad341","small":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=2212f2146d236f7de493e34f1ae1088a","thumb":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=9bac5f792bbe4962e6911defa35ca379"},"links":{"self":"https://api.unsplash.com/photos/QY300DHjPaQ","html":"https://unsplash.com/photos/QY300DHjPaQ","download":"https://unsplash.com/photos/QY300DHjPaQ/download","download_location":"https://api.unsplash.com/photos/QY300DHjPaQ/download"},"liked_by_user":false,"sponsored":false,"likes":394,"user":{"id":"oY46cAl6JV4","updated_at":"2018-04-27T00:27:35-04:00","username":"robertina","name":"Roberta Sorge","first_name":"Roberta","last_name":"Sorge","twitter_username":null,"portfolio_url":"https://www.instagram.com/robertasorge_/","bio":null,"location":null,"links":{"self":"https://api.unsplash.com/users/robertina","html":"https://unsplash.com/@robertina","photos":"https://api.unsplash.com/users/robertina/photos","likes":"https://api.unsplash.com/users/robertina/likes","portfolio":"https://api.unsplash.com/users/robertina/portfolio","following":"https://api.unsplash.com/users/robertina/following","followers":"https://api.unsplash.com/users/robertina/followers"},"profile_image":{"small":"https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=79316fdcfc60f8ee549009def5f69c7f","medium":"https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=8fa60bd04c76ae09079576d94fcd9e5f","large":"https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=5ac46f51cddf728ec2d56494f80f3ab3"},"total_collections":0,"instagram_username":"robertasorge_","total_likes":22,"total_photos":36}}
         * preview_photos : [{"id":125549,"urls":{"raw":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&s=a1830a53787fe5d0bdd8c1716d42de93","full":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=275a4274f5bb3ada7ef8efef72a4ec41","regular":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=36121aa29531e73d438242130aaad341","small":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=2212f2146d236f7de493e34f1ae1088a","thumb":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=9bac5f792bbe4962e6911defa35ca379"}},{"id":124160,"urls":{"raw":"https://images.unsplash.com/photo-1471248026681-35a45d5530a3?ixlib=rb-0.3.5&s=69ae2f9dd24d3516e6fd3757388ea573","full":"https://images.unsplash.com/photo-1471248026681-35a45d5530a3?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=f2f02c85fd1f878398484e778f7cacf9","regular":"https://images.unsplash.com/photo-1471248026681-35a45d5530a3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=55d6684b790c8088a2b766f65a022636","small":"https://images.unsplash.com/photo-1471248026681-35a45d5530a3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=3e83ad9bcc54205ab9682f278f1d2b2c","thumb":"https://images.unsplash.com/photo-1471248026681-35a45d5530a3?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=e3b6320d2c4f07c4aff6577ba13ee456"}},{"id":123631,"urls":{"raw":"https://images.unsplash.com/photo-1471113082645-fde63c139087?ixlib=rb-0.3.5&s=1b63b3b1b7d05fd00272cd81f66981c8","full":"https://images.unsplash.com/photo-1471113082645-fde63c139087?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=3fdcb51262f890c1753dda5f46611e0b","regular":"https://images.unsplash.com/photo-1471113082645-fde63c139087?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=55d50d9aa9d47f5039eac8838ab0e781","small":"https://images.unsplash.com/photo-1471113082645-fde63c139087?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=e8618760539f29dc65823dfcea0ea057","thumb":"https://images.unsplash.com/photo-1471113082645-fde63c139087?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=7e5f0e913d0355d0a07da56c2f7f2e99"}},{"id":123624,"urls":{"raw":"https://images.unsplash.com/photo-1471111471478-429bc38f2da4?ixlib=rb-0.3.5&s=65cf26e71f7c07910b1c8e1d0d16df6d","full":"https://images.unsplash.com/photo-1471111471478-429bc38f2da4?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=d06056cde3fdd9d2028b2691d56cf890","regular":"https://images.unsplash.com/photo-1471111471478-429bc38f2da4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=7f04625bd976373b8700023456343156","small":"https://images.unsplash.com/photo-1471111471478-429bc38f2da4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=b0be3f8580c5c90772bae21a925b25c3","thumb":"https://images.unsplash.com/photo-1471111471478-429bc38f2da4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=7fa2c27fd2107a9063692a353fd3905b"}}]
         * user : {"id":"x5oeUQw83Gk","updated_at":"2018-04-29T16:18:05-04:00","username":"heatherd","name":"Heather Dou","first_name":"Heather","last_name":"Dou","twitter_username":null,"portfolio_url":null,"bio":null,"location":null,"links":{"self":"https://api.unsplash.com/users/heatherd","html":"https://unsplash.com/@heatherd","photos":"https://api.unsplash.com/users/heatherd/photos","likes":"https://api.unsplash.com/users/heatherd/likes","portfolio":"https://api.unsplash.com/users/heatherd/portfolio","following":"https://api.unsplash.com/users/heatherd/following","followers":"https://api.unsplash.com/users/heatherd/followers"},"profile_image":{"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"},"total_collections":23,"instagram_username":null,"total_likes":128,"total_photos":0}
         * links : {"self":"https://api.unsplash.com/collections/202618","html":"https://unsplash.com/collections/202618/fruits-vegetables","photos":"https://api.unsplash.com/collections/202618/photos","related":"https://api.unsplash.com/collections/202618/related"}
         */

        private int id;
        private String title;
        private Object description;
        private String published_at;
        private String updated_at;
        private boolean curated;
        private boolean featured;
        private int total_photos;
        @JsonProperty("private")
        private boolean privateX;
        private String share_key;
        private CoverPhotoDTO cover_photo;
        private UserDTOX user;
        private LinksDTOXXX links;
        private List<TagsDTO> tags;
        private List<PreviewPhotosDTO> preview_photos;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getDescription() {
            return description;
        }

        public void setDescription(Object description) {
            this.description = description;
        }

        public String getPublished_at() {
            return published_at;
        }

        public void setPublished_at(String published_at) {
            this.published_at = published_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public boolean isCurated() {
            return curated;
        }

        public void setCurated(boolean curated) {
            this.curated = curated;
        }

        public boolean isFeatured() {
            return featured;
        }

        public void setFeatured(boolean featured) {
            this.featured = featured;
        }

        public int getTotal_photos() {
            return total_photos;
        }

        public void setTotal_photos(int total_photos) {
            this.total_photos = total_photos;
        }

        public boolean isPrivateX() {
            return privateX;
        }

        public void setPrivateX(boolean privateX) {
            this.privateX = privateX;
        }

        public String getShare_key() {
            return share_key;
        }

        public void setShare_key(String share_key) {
            this.share_key = share_key;
        }

        public CoverPhotoDTO getCover_photo() {
            return cover_photo;
        }

        public void setCover_photo(CoverPhotoDTO cover_photo) {
            this.cover_photo = cover_photo;
        }

        public UserDTOX getUser() {
            return user;
        }

        public void setUser(UserDTOX user) {
            this.user = user;
        }

        public LinksDTOXXX getLinks() {
            return links;
        }

        public void setLinks(LinksDTOXXX links) {
            this.links = links;
        }

        public List<TagsDTO> getTags() {
            return tags;
        }

        public void setTags(List<TagsDTO> tags) {
            this.tags = tags;
        }

        public List<PreviewPhotosDTO> getPreview_photos() {
            return preview_photos;
        }

        public void setPreview_photos(List<PreviewPhotosDTO> preview_photos) {
            this.preview_photos = preview_photos;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class CoverPhotoDTO {
            /**
             * id : QY300DHjPaQ
             * created_at : 2016-08-18T08:45:23-04:00
             * updated_at : 2017-11-01T04:18:55-04:00
             * width : 4272
             * height : 2848
             * color : #D7DCE3
             * description : Glass bowl of fresh red strawberries
             * categories : []
             * urls : {"raw":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&s=a1830a53787fe5d0bdd8c1716d42de93","full":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=275a4274f5bb3ada7ef8efef72a4ec41","regular":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=36121aa29531e73d438242130aaad341","small":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=2212f2146d236f7de493e34f1ae1088a","thumb":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=9bac5f792bbe4962e6911defa35ca379"}
             * links : {"self":"https://api.unsplash.com/photos/QY300DHjPaQ","html":"https://unsplash.com/photos/QY300DHjPaQ","download":"https://unsplash.com/photos/QY300DHjPaQ/download","download_location":"https://api.unsplash.com/photos/QY300DHjPaQ/download"}
             * liked_by_user : false
             * sponsored : false
             * likes : 394
             * user : {"id":"oY46cAl6JV4","updated_at":"2018-04-27T00:27:35-04:00","username":"robertina","name":"Roberta Sorge","first_name":"Roberta","last_name":"Sorge","twitter_username":null,"portfolio_url":"https://www.instagram.com/robertasorge_/","bio":null,"location":null,"links":{"self":"https://api.unsplash.com/users/robertina","html":"https://unsplash.com/@robertina","photos":"https://api.unsplash.com/users/robertina/photos","likes":"https://api.unsplash.com/users/robertina/likes","portfolio":"https://api.unsplash.com/users/robertina/portfolio","following":"https://api.unsplash.com/users/robertina/following","followers":"https://api.unsplash.com/users/robertina/followers"},"profile_image":{"small":"https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=79316fdcfc60f8ee549009def5f69c7f","medium":"https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=8fa60bd04c76ae09079576d94fcd9e5f","large":"https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=5ac46f51cddf728ec2d56494f80f3ab3"},"total_collections":0,"instagram_username":"robertasorge_","total_likes":22,"total_photos":36}
             */

            private String id;
            private String created_at;
            private String updated_at;
            private int width;
            private int height;
            private String color;
            private String description;
            private UrlsDTO urls;
            private LinksDTO links;
            private boolean liked_by_user;
            private boolean sponsored;
            private int likes;
            private UserDTO user;
            private List<?> categories;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public UrlsDTO getUrls() {
                return urls;
            }

            public void setUrls(UrlsDTO urls) {
                this.urls = urls;
            }

            public LinksDTO getLinks() {
                return links;
            }

            public void setLinks(LinksDTO links) {
                this.links = links;
            }

            public boolean isLiked_by_user() {
                return liked_by_user;
            }

            public void setLiked_by_user(boolean liked_by_user) {
                this.liked_by_user = liked_by_user;
            }

            public boolean isSponsored() {
                return sponsored;
            }

            public void setSponsored(boolean sponsored) {
                this.sponsored = sponsored;
            }

            public int getLikes() {
                return likes;
            }

            public void setLikes(int likes) {
                this.likes = likes;
            }

            public UserDTO getUser() {
                return user;
            }

            public void setUser(UserDTO user) {
                this.user = user;
            }

            public List<?> getCategories() {
                return categories;
            }

            public void setCategories(List<?> categories) {
                this.categories = categories;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class UrlsDTO {
                /**
                 * raw : https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&s=a1830a53787fe5d0bdd8c1716d42de93
                 * full : https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=275a4274f5bb3ada7ef8efef72a4ec41
                 * regular : https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=36121aa29531e73d438242130aaad341
                 * small : https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=2212f2146d236f7de493e34f1ae1088a
                 * thumb : https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=9bac5f792bbe4962e6911defa35ca379
                 */

                private String raw;
                private String full;
                private String regular;
                private String small;
                private String thumb;

                public String getRaw() {
                    return raw;
                }

                public void setRaw(String raw) {
                    this.raw = raw;
                }

                public String getFull() {
                    return full;
                }

                public void setFull(String full) {
                    this.full = full;
                }

                public String getRegular() {
                    return regular;
                }

                public void setRegular(String regular) {
                    this.regular = regular;
                }

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class LinksDTO {
                /**
                 * self : https://api.unsplash.com/photos/QY300DHjPaQ
                 * html : https://unsplash.com/photos/QY300DHjPaQ
                 * download : https://unsplash.com/photos/QY300DHjPaQ/download
                 * download_location : https://api.unsplash.com/photos/QY300DHjPaQ/download
                 */

                private String self;
                private String html;
                private String download;
                private String download_location;

                public String getSelf() {
                    return self;
                }

                public void setSelf(String self) {
                    this.self = self;
                }

                public String getHtml() {
                    return html;
                }

                public void setHtml(String html) {
                    this.html = html;
                }

                public String getDownload() {
                    return download;
                }

                public void setDownload(String download) {
                    this.download = download;
                }

                public String getDownload_location() {
                    return download_location;
                }

                public void setDownload_location(String download_location) {
                    this.download_location = download_location;
                }
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class UserDTO {
                /**
                 * id : oY46cAl6JV4
                 * updated_at : 2018-04-27T00:27:35-04:00
                 * username : robertina
                 * name : Roberta Sorge
                 * first_name : Roberta
                 * last_name : Sorge
                 * twitter_username : null
                 * portfolio_url : https://www.instagram.com/robertasorge_/
                 * bio : null
                 * location : null
                 * links : {"self":"https://api.unsplash.com/users/robertina","html":"https://unsplash.com/@robertina","photos":"https://api.unsplash.com/users/robertina/photos","likes":"https://api.unsplash.com/users/robertina/likes","portfolio":"https://api.unsplash.com/users/robertina/portfolio","following":"https://api.unsplash.com/users/robertina/following","followers":"https://api.unsplash.com/users/robertina/followers"}
                 * profile_image : {"small":"https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=79316fdcfc60f8ee549009def5f69c7f","medium":"https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=8fa60bd04c76ae09079576d94fcd9e5f","large":"https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=5ac46f51cddf728ec2d56494f80f3ab3"}
                 * total_collections : 0
                 * instagram_username : robertasorge_
                 * total_likes : 22
                 * total_photos : 36
                 */

                private String id;
                private String updated_at;
                private String username;
                private String name;
                private String first_name;
                private String last_name;
                private Object twitter_username;
                private String portfolio_url;
                private Object bio;
                private Object location;
                private LinksDTOX links;
                private ProfileImageDTO profile_image;
                private int total_collections;
                private String instagram_username;
                private int total_likes;
                private int total_photos;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(String updated_at) {
                    this.updated_at = updated_at;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getFirst_name() {
                    return first_name;
                }

                public void setFirst_name(String first_name) {
                    this.first_name = first_name;
                }

                public String getLast_name() {
                    return last_name;
                }

                public void setLast_name(String last_name) {
                    this.last_name = last_name;
                }

                public Object getTwitter_username() {
                    return twitter_username;
                }

                public void setTwitter_username(Object twitter_username) {
                    this.twitter_username = twitter_username;
                }

                public String getPortfolio_url() {
                    return portfolio_url;
                }

                public void setPortfolio_url(String portfolio_url) {
                    this.portfolio_url = portfolio_url;
                }

                public Object getBio() {
                    return bio;
                }

                public void setBio(Object bio) {
                    this.bio = bio;
                }

                public Object getLocation() {
                    return location;
                }

                public void setLocation(Object location) {
                    this.location = location;
                }

                public LinksDTOX getLinks() {
                    return links;
                }

                public void setLinks(LinksDTOX links) {
                    this.links = links;
                }

                public ProfileImageDTO getProfile_image() {
                    return profile_image;
                }

                public void setProfile_image(ProfileImageDTO profile_image) {
                    this.profile_image = profile_image;
                }

                public int getTotal_collections() {
                    return total_collections;
                }

                public void setTotal_collections(int total_collections) {
                    this.total_collections = total_collections;
                }

                public String getInstagram_username() {
                    return instagram_username;
                }

                public void setInstagram_username(String instagram_username) {
                    this.instagram_username = instagram_username;
                }

                public int getTotal_likes() {
                    return total_likes;
                }

                public void setTotal_likes(int total_likes) {
                    this.total_likes = total_likes;
                }

                public int getTotal_photos() {
                    return total_photos;
                }

                public void setTotal_photos(int total_photos) {
                    this.total_photos = total_photos;
                }

                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class LinksDTOX {
                    /**
                     * self : https://api.unsplash.com/users/robertina
                     * html : https://unsplash.com/@robertina
                     * photos : https://api.unsplash.com/users/robertina/photos
                     * likes : https://api.unsplash.com/users/robertina/likes
                     * portfolio : https://api.unsplash.com/users/robertina/portfolio
                     * following : https://api.unsplash.com/users/robertina/following
                     * followers : https://api.unsplash.com/users/robertina/followers
                     */

                    private String self;
                    private String html;
                    private String photos;
                    private String likes;
                    private String portfolio;
                    private String following;
                    private String followers;

                    public String getSelf() {
                        return self;
                    }

                    public void setSelf(String self) {
                        this.self = self;
                    }

                    public String getHtml() {
                        return html;
                    }

                    public void setHtml(String html) {
                        this.html = html;
                    }

                    public String getPhotos() {
                        return photos;
                    }

                    public void setPhotos(String photos) {
                        this.photos = photos;
                    }

                    public String getLikes() {
                        return likes;
                    }

                    public void setLikes(String likes) {
                        this.likes = likes;
                    }

                    public String getPortfolio() {
                        return portfolio;
                    }

                    public void setPortfolio(String portfolio) {
                        this.portfolio = portfolio;
                    }

                    public String getFollowing() {
                        return following;
                    }

                    public void setFollowing(String following) {
                        this.following = following;
                    }

                    public String getFollowers() {
                        return followers;
                    }

                    public void setFollowers(String followers) {
                        this.followers = followers;
                    }
                }

                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class ProfileImageDTO {
                    /**
                     * small : https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=79316fdcfc60f8ee549009def5f69c7f
                     * medium : https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=8fa60bd04c76ae09079576d94fcd9e5f
                     * large : https://images.unsplash.com/profile-1509317909576-015fde96f53c?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=5ac46f51cddf728ec2d56494f80f3ab3
                     */

                    private String small;
                    private String medium;
                    private String large;

                    public String getSmall() {
                        return small;
                    }

                    public void setSmall(String small) {
                        this.small = small;
                    }

                    public String getMedium() {
                        return medium;
                    }

                    public void setMedium(String medium) {
                        this.medium = medium;
                    }

                    public String getLarge() {
                        return large;
                    }

                    public void setLarge(String large) {
                        this.large = large;
                    }
                }
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class UserDTOX {
            /**
             * id : x5oeUQw83Gk
             * updated_at : 2018-04-29T16:18:05-04:00
             * username : heatherd
             * name : Heather Dou
             * first_name : Heather
             * last_name : Dou
             * twitter_username : null
             * portfolio_url : null
             * bio : null
             * location : null
             * links : {"self":"https://api.unsplash.com/users/heatherd","html":"https://unsplash.com/@heatherd","photos":"https://api.unsplash.com/users/heatherd/photos","likes":"https://api.unsplash.com/users/heatherd/likes","portfolio":"https://api.unsplash.com/users/heatherd/portfolio","following":"https://api.unsplash.com/users/heatherd/following","followers":"https://api.unsplash.com/users/heatherd/followers"}
             * profile_image : {"small":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc","medium":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358","large":"https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d"}
             * total_collections : 23
             * instagram_username : null
             * total_likes : 128
             * total_photos : 0
             */

            private String id;
            private String updated_at;
            private String username;
            private String name;
            private String first_name;
            private String last_name;
            private Object twitter_username;
            private Object portfolio_url;
            private Object bio;
            private Object location;
            private LinksDTOXX links;
            private ProfileImageDTOX profile_image;
            private int total_collections;
            private Object instagram_username;
            private int total_likes;
            private int total_photos;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFirst_name() {
                return first_name;
            }

            public void setFirst_name(String first_name) {
                this.first_name = first_name;
            }

            public String getLast_name() {
                return last_name;
            }

            public void setLast_name(String last_name) {
                this.last_name = last_name;
            }

            public Object getTwitter_username() {
                return twitter_username;
            }

            public void setTwitter_username(Object twitter_username) {
                this.twitter_username = twitter_username;
            }

            public Object getPortfolio_url() {
                return portfolio_url;
            }

            public void setPortfolio_url(Object portfolio_url) {
                this.portfolio_url = portfolio_url;
            }

            public Object getBio() {
                return bio;
            }

            public void setBio(Object bio) {
                this.bio = bio;
            }

            public Object getLocation() {
                return location;
            }

            public void setLocation(Object location) {
                this.location = location;
            }

            public LinksDTOXX getLinks() {
                return links;
            }

            public void setLinks(LinksDTOXX links) {
                this.links = links;
            }

            public ProfileImageDTOX getProfile_image() {
                return profile_image;
            }

            public void setProfile_image(ProfileImageDTOX profile_image) {
                this.profile_image = profile_image;
            }

            public int getTotal_collections() {
                return total_collections;
            }

            public void setTotal_collections(int total_collections) {
                this.total_collections = total_collections;
            }

            public Object getInstagram_username() {
                return instagram_username;
            }

            public void setInstagram_username(Object instagram_username) {
                this.instagram_username = instagram_username;
            }

            public int getTotal_likes() {
                return total_likes;
            }

            public void setTotal_likes(int total_likes) {
                this.total_likes = total_likes;
            }

            public int getTotal_photos() {
                return total_photos;
            }

            public void setTotal_photos(int total_photos) {
                this.total_photos = total_photos;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class LinksDTOXX {
                /**
                 * self : https://api.unsplash.com/users/heatherd
                 * html : https://unsplash.com/@heatherd
                 * photos : https://api.unsplash.com/users/heatherd/photos
                 * likes : https://api.unsplash.com/users/heatherd/likes
                 * portfolio : https://api.unsplash.com/users/heatherd/portfolio
                 * following : https://api.unsplash.com/users/heatherd/following
                 * followers : https://api.unsplash.com/users/heatherd/followers
                 */

                private String self;
                private String html;
                private String photos;
                private String likes;
                private String portfolio;
                private String following;
                private String followers;

                public String getSelf() {
                    return self;
                }

                public void setSelf(String self) {
                    this.self = self;
                }

                public String getHtml() {
                    return html;
                }

                public void setHtml(String html) {
                    this.html = html;
                }

                public String getPhotos() {
                    return photos;
                }

                public void setPhotos(String photos) {
                    this.photos = photos;
                }

                public String getLikes() {
                    return likes;
                }

                public void setLikes(String likes) {
                    this.likes = likes;
                }

                public String getPortfolio() {
                    return portfolio;
                }

                public void setPortfolio(String portfolio) {
                    this.portfolio = portfolio;
                }

                public String getFollowing() {
                    return following;
                }

                public void setFollowing(String following) {
                    this.following = following;
                }

                public String getFollowers() {
                    return followers;
                }

                public void setFollowers(String followers) {
                    this.followers = followers;
                }
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class ProfileImageDTOX {
                /**
                 * small : https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=0ad68f44c4725d5a3fda019bab9d3edc
                 * medium : https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=356bd4b76a3d4eb97d63f45b818dd358
                 * large : https://images.unsplash.com/placeholder-avatars/extra-large.jpg?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=ee8bbf5fb8d6e43aaaa238feae2fe90d
                 */

                private String small;
                private String medium;
                private String large;

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getMedium() {
                    return medium;
                }

                public void setMedium(String medium) {
                    this.medium = medium;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class LinksDTOXXX {
            /**
             * self : https://api.unsplash.com/collections/202618
             * html : https://unsplash.com/collections/202618/fruits-vegetables
             * photos : https://api.unsplash.com/collections/202618/photos
             * related : https://api.unsplash.com/collections/202618/related
             */

            private String self;
            private String html;
            private String photos;
            private String related;

            public String getSelf() {
                return self;
            }

            public void setSelf(String self) {
                this.self = self;
            }

            public String getHtml() {
                return html;
            }

            public void setHtml(String html) {
                this.html = html;
            }

            public String getPhotos() {
                return photos;
            }

            public void setPhotos(String photos) {
                this.photos = photos;
            }

            public String getRelated() {
                return related;
            }

            public void setRelated(String related) {
                this.related = related;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class TagsDTO {
            /**
             * title : vegetable
             */

            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class PreviewPhotosDTO {
            /**
             * id : 125549
             * urls : {"raw":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&s=a1830a53787fe5d0bdd8c1716d42de93","full":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=275a4274f5bb3ada7ef8efef72a4ec41","regular":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=36121aa29531e73d438242130aaad341","small":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=2212f2146d236f7de493e34f1ae1088a","thumb":"https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=9bac5f792bbe4962e6911defa35ca379"}
             */

            private int id;
            private UrlsDTOX urls;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public UrlsDTOX getUrls() {
                return urls;
            }

            public void setUrls(UrlsDTOX urls) {
                this.urls = urls;
            }

            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class UrlsDTOX {
                /**
                 * raw : https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&s=a1830a53787fe5d0bdd8c1716d42de93
                 * full : https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&s=275a4274f5bb3ada7ef8efef72a4ec41
                 * regular : https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&s=36121aa29531e73d438242130aaad341
                 * small : https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&s=2212f2146d236f7de493e34f1ae1088a
                 * thumb : https://images.unsplash.com/photo-1471524279812-11f70243a736?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&s=9bac5f792bbe4962e6911defa35ca379
                 */

                private String raw;
                private String full;
                private String regular;
                private String small;
                private String thumb;

                public String getRaw() {
                    return raw;
                }

                public void setRaw(String raw) {
                    this.raw = raw;
                }

                public String getFull() {
                    return full;
                }

                public void setFull(String full) {
                    this.full = full;
                }

                public String getRegular() {
                    return regular;
                }

                public void setRegular(String regular) {
                    this.regular = regular;
                }

                public String getSmall() {
                    return small;
                }

                public void setSmall(String small) {
                    this.small = small;
                }

                public String getThumb() {
                    return thumb;
                }

                public void setThumb(String thumb) {
                    this.thumb = thumb;
                }
            }
        }
    }

    /**
     * 获取原图
     * @return
     */
    public List<String> raw() {
        return this.results.stream().map(e->e.cover_photo.urls.raw).collect(Collectors.toList());
    }

    /**
     * 缩小尺寸
     * @return
     */
    public List<String> small() {
        return this.results.stream().map(e->e.cover_photo.urls.small).collect(Collectors.toList());
    }

    /**
     * 常规尺寸
     * @return
     */
    public List<String> regular() {
        return this.results.stream().map(e->e.cover_photo.urls.regular).collect(Collectors.toList());
    }

    /**
     * 索引
     * @return
     */
    public List<String> thumb() {
        return this.results.stream().map(e->e.cover_photo.urls.thumb).collect(Collectors.toList());
    }

    /**
     * 完整原图
     * @return
     */
    public List<String> full() {
        return this.results.stream().map(e->e.cover_photo.urls.full).collect(Collectors.toList());
    }
}
