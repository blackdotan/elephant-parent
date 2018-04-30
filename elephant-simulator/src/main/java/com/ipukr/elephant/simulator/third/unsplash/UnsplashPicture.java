package com.ipukr.elephant.simulator.third.unsplash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * 请描述类 <br>
 *
 * @author ryan wu
 * <p>
 * Created by ryan wu on 2018/5/1.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UnsplashPicture {


    /**
     * id : d2MSDujJl2g
     * created_at : 2017-07-13T21:49:17-04:00
     * updated_at : 2017-11-01T14:04:35-04:00
     * width : 4516
     * height : 5622
     * color : #E9CCB6
     * description : Young man with freckles, green eyes, and tousled hair
     * categories : []
     * urls : {"raw":"https://images.unsplash.com/photo-1499996860823-5214fcc65f8f?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjI1NzI3fQ&s=50a1b46855227e4e7c09194a509309e1","full":"https://images.unsplash.com/photo-1499996860823-5214fcc65f8f?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjI1NzI3fQ&s=1a7ae383e130d33859bad10404eaaf92","regular":"https://images.unsplash.com/photo-1499996860823-5214fcc65f8f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjI1NzI3fQ&s=85499741160241894d6701269f9803c6","small":"https://images.unsplash.com/photo-1499996860823-5214fcc65f8f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjI1NzI3fQ&s=805d23e028ce8b008ca735ff33825f1f","thumb":"https://images.unsplash.com/photo-1499996860823-5214fcc65f8f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjI1NzI3fQ&s=8c2b33d8fe6fb0c013e7d2a49e1bff8e"}
     * links : {"self":"https://api.unsplash.com/photos/d2MSDujJl2g","html":"https://unsplash.com/photos/d2MSDujJl2g","download":"https://unsplash.com/photos/d2MSDujJl2g/download","download_location":"https://api.unsplash.com/photos/d2MSDujJl2g/download"}
     * liked_by_user : false
     * sponsored : false
     * likes : 919
     * user : {"id":"JJg2_TG9VYw","updated_at":"2018-04-27T16:02:39-04:00","username":"erik_lucatero","name":"Erik  Lucatero","first_name":"Erik ","last_name":"Lucatero","twitter_username":null,"portfolio_url":"http://www.eriklucatero.com","bio":"My friends and I,  love going out and making films and taking photos. If you dont mind please back link my Instagram.Want to support my work click my website. Then buy me a coke!😀","location":"South Florida","links":{"self":"https://api.unsplash.com/users/erik_lucatero","html":"https://unsplash.com/@erik_lucatero","photos":"https://api.unsplash.com/users/erik_lucatero/photos","likes":"https://api.unsplash.com/users/erik_lucatero/likes","portfolio":"https://api.unsplash.com/users/erik_lucatero/portfolio","following":"https://api.unsplash.com/users/erik_lucatero/following","followers":"https://api.unsplash.com/users/erik_lucatero/followers"},"profile_image":{"small":"https://images.unsplash.com/profile-1524859358747-2fed3690d7a4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=2f08dfbe6020b05d453dadd8073b0496","medium":"https://images.unsplash.com/profile-1524859358747-2fed3690d7a4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=77ac1ed6fff7cd60f2d245e4104ee2d7","large":"https://images.unsplash.com/profile-1524859358747-2fed3690d7a4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=1e452f5b175f4429fe914979f0a02eb4"},"total_collections":0,"instagram_username":"everydaysafilm","total_likes":2,"total_photos":25}
     * current_user_collections : []
     * slug : null
     * exif : {"make":"Sony","model":"ILCE-6500","exposure_time":"1/50","aperture":"2","focal_length":"35","iso":1250}
     * views : 4488085
     * downloads : 20899
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
    private Object slug;
    private ExifDTO exif;
    private int views;
    private int downloads;
    private List<?> categories;
    private List<?> current_user_collections;

    /**
     * 获取原图
     * @return
     */
    public String raw() {
        return this.urls.raw;
    }

    /**
     * 缩小尺寸
     * @return
     */
    public String small() {
        return this.urls.small;
    }

    /**
     * 常规尺寸
     * @return
     */
    public String regular() {
        return this.urls.regular;
    }

    /**
     * 索引
     * @return
     */
    public String thumb() {
        return this.urls.thumb;
    }

    /**
     * 完整原图
     * @return
     */
    public String full() {
        return this.urls.full;
    }

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

    public Object getSlug() {
        return slug;
    }

    public void setSlug(Object slug) {
        this.slug = slug;
    }

    public ExifDTO getExif() {
        return exif;
    }

    public void setExif(ExifDTO exif) {
        this.exif = exif;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setDownloads(int downloads) {
        this.downloads = downloads;
    }

    public List<?> getCategories() {
        return categories;
    }

    public void setCategories(List<?> categories) {
        this.categories = categories;
    }

    public List<?> getCurrent_user_collections() {
        return current_user_collections;
    }

    public void setCurrent_user_collections(List<?> current_user_collections) {
        this.current_user_collections = current_user_collections;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class UrlsDTO {
        /**
         * raw : https://images.unsplash.com/photo-1499996860823-5214fcc65f8f?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjI1NzI3fQ&s=50a1b46855227e4e7c09194a509309e1
         * full : https://images.unsplash.com/photo-1499996860823-5214fcc65f8f?ixlib=rb-0.3.5&q=85&fm=jpg&crop=entropy&cs=srgb&ixid=eyJhcHBfaWQiOjI1NzI3fQ&s=1a7ae383e130d33859bad10404eaaf92
         * regular : https://images.unsplash.com/photo-1499996860823-5214fcc65f8f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=1080&fit=max&ixid=eyJhcHBfaWQiOjI1NzI3fQ&s=85499741160241894d6701269f9803c6
         * small : https://images.unsplash.com/photo-1499996860823-5214fcc65f8f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjI1NzI3fQ&s=805d23e028ce8b008ca735ff33825f1f
         * thumb : https://images.unsplash.com/photo-1499996860823-5214fcc65f8f?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=200&fit=max&ixid=eyJhcHBfaWQiOjI1NzI3fQ&s=8c2b33d8fe6fb0c013e7d2a49e1bff8e
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
         * self : https://api.unsplash.com/photos/d2MSDujJl2g
         * html : https://unsplash.com/photos/d2MSDujJl2g
         * download : https://unsplash.com/photos/d2MSDujJl2g/download
         * download_location : https://api.unsplash.com/photos/d2MSDujJl2g/download
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
         * id : JJg2_TG9VYw
         * updated_at : 2018-04-27T16:02:39-04:00
         * username : erik_lucatero
         * name : Erik  Lucatero
         * first_name : Erik
         * last_name : Lucatero
         * twitter_username : null
         * portfolio_url : http://www.eriklucatero.com
         * bio : My friends and I,  love going out and making films and taking photos. If you dont mind please back link my Instagram.Want to support my work click my website. Then buy me a coke!😀
         * location : South Florida
         * links : {"self":"https://api.unsplash.com/users/erik_lucatero","html":"https://unsplash.com/@erik_lucatero","photos":"https://api.unsplash.com/users/erik_lucatero/photos","likes":"https://api.unsplash.com/users/erik_lucatero/likes","portfolio":"https://api.unsplash.com/users/erik_lucatero/portfolio","following":"https://api.unsplash.com/users/erik_lucatero/following","followers":"https://api.unsplash.com/users/erik_lucatero/followers"}
         * profile_image : {"small":"https://images.unsplash.com/profile-1524859358747-2fed3690d7a4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=2f08dfbe6020b05d453dadd8073b0496","medium":"https://images.unsplash.com/profile-1524859358747-2fed3690d7a4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=77ac1ed6fff7cd60f2d245e4104ee2d7","large":"https://images.unsplash.com/profile-1524859358747-2fed3690d7a4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=1e452f5b175f4429fe914979f0a02eb4"}
         * total_collections : 0
         * instagram_username : everydaysafilm
         * total_likes : 2
         * total_photos : 25
         */

        private String id;
        private String updated_at;
        private String username;
        private String name;
        private String first_name;
        private String last_name;
        private Object twitter_username;
        private String portfolio_url;
        private String bio;
        private String location;
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

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
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
             * self : https://api.unsplash.com/users/erik_lucatero
             * html : https://unsplash.com/@erik_lucatero
             * photos : https://api.unsplash.com/users/erik_lucatero/photos
             * likes : https://api.unsplash.com/users/erik_lucatero/likes
             * portfolio : https://api.unsplash.com/users/erik_lucatero/portfolio
             * following : https://api.unsplash.com/users/erik_lucatero/following
             * followers : https://api.unsplash.com/users/erik_lucatero/followers
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
             * small : https://images.unsplash.com/profile-1524859358747-2fed3690d7a4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=32&w=32&s=2f08dfbe6020b05d453dadd8073b0496
             * medium : https://images.unsplash.com/profile-1524859358747-2fed3690d7a4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=77ac1ed6fff7cd60f2d245e4104ee2d7
             * large : https://images.unsplash.com/profile-1524859358747-2fed3690d7a4?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=128&w=128&s=1e452f5b175f4429fe914979f0a02eb4
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
    public static class ExifDTO {
        /**
         * make : Sony
         * model : ILCE-6500
         * exposure_time : 1/50
         * aperture : 2
         * focal_length : 35
         * iso : 1250
         */

        private String make;
        private String model;
        private String exposure_time;
        private String aperture;
        private String focal_length;
        private int iso;

        public String getMake() {
            return make;
        }

        public void setMake(String make) {
            this.make = make;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getExposure_time() {
            return exposure_time;
        }

        public void setExposure_time(String exposure_time) {
            this.exposure_time = exposure_time;
        }

        public String getAperture() {
            return aperture;
        }

        public void setAperture(String aperture) {
            this.aperture = aperture;
        }

        public String getFocal_length() {
            return focal_length;
        }

        public void setFocal_length(String focal_length) {
            this.focal_length = focal_length;
        }

        public int getIso() {
            return iso;
        }

        public void setIso(int iso) {
            this.iso = iso;
        }
    }
}
