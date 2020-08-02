package com.example.youtubebg.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Search_Response {
    private String kind, etag, nextPageToken;
    private PageInfo pageInfo;

    private List<Item> items = new ArrayList<>();

    public String getKind() {
        return kind;
    }

    public String getEtag() {
        return etag;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public List<Item> getItems() {
        return items;
    }




    public static class PageInfo {
        private int totalResults, resultsPerPage;

        public int getTotalResults() {
            return totalResults;
        }

        public int getResultsPerPage() {
            return resultsPerPage;
        }
    }


    public static class Item {
        private String kind, etag;
        private Snippet snippet;
        private Id id;
        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }

        public void setEtag(String etag) {
            this.etag = etag;
        }

        public void setSnippet(Snippet snippet) {
            this.snippet = snippet;
        }

        public Id getId() {
            return id;
        }

        public void setId(Id id) {
            this.id = id;
        }

        public String getEtag() {
            return etag;
        }

        public Snippet getSnippet() {
            return snippet;
        }

        public static class Id {
            private String kind;
            private String videoId;

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
            }

            public String getVideoId() {
                return videoId;
            }

            public void setVideoId(String videoId) {
                this.videoId = videoId;
            }

            public String getChannelId() {
                return channelId;
            }

            public void setChannelId(String channelId) {
                this.channelId = channelId;
            }

            public String getPlaylistId() {
                return playlistId;
            }

            public void setPlaylistId(String playlistId) {
                this.playlistId = playlistId;
            }

            @SerializedName("channelId")
            private  String channelId;
            private String playlistId;

        }


        public static class Snippet {
            private String publishedAt, channelId, title, description, channelTitle, categoryId, liveBroadcastContent;
            private Thumbnails thumbnails;
            private List<String> tags = new ArrayList<>();

            public String getPublishedAt() {
                return publishedAt;
            }

            public String getChannelId() {
                return channelId;
            }

            public String getTitle() {
                return title;
            }

            public String getDescription() {
                return description;
            }

            public String getChannelTitle() {
                return channelTitle;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public String getLiveBroadcastContent() {
                return liveBroadcastContent;
            }

            public Thumbnails getThumbnails() {
                return thumbnails;
            }

            public List<String> getTags() {
                return tags;
            }



            public static class Thumbnails {
                @SerializedName("high")
                private High high;

                public High getDefault() {
                    return high;
                }

                public static final class High {
                    private String url;
                    private int width, height;

                    public String getUrl() {
                        return url;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public int getHeight() {
                        return height;
                    }
                }
            }
        }
    }


}

