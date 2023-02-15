package com.danvento.heartthisatapi.data.model
import com.google.gson.annotations.SerializedName


class TrackList : ArrayList<TrackList.Track>(){
    data class Track(
        @SerializedName("artwork_url")
        val artworkUrl: String? = null,
        @SerializedName("artwork_url_retina")
        val artworkUrlRetina: String? = null,
        @SerializedName("background_url")
        val backgroundUrl: String? = null,
        @SerializedName("bpm")
        val bpm: String? = null,
        @SerializedName("comment_count")
        val commentCount: Int? = null,
        @SerializedName("created_at")
        val createdAt: String? = null,
        @SerializedName("description")
        val description: String? = null,
        @SerializedName("download_count")
        val downloadCount: Int? = null,
        @SerializedName("download_filename")
        val downloadFilename: String? = null,
        @SerializedName("download_url")
        val downloadUrl: String? = null,
        @SerializedName("downloadable")
        val downloadable: Int? = null,
        @SerializedName("duration")
        val duration: String? = null,
        @SerializedName("fan_exclusive_download")
        val fanExclusiveDownload: Int? = null,
        @SerializedName("fan_exclusive_play")
        val fanExclusivePlay: Int? = null,
        @SerializedName("favorited")
        val favorited: Boolean? = null,
        @SerializedName("favoritings_count")
        val favoritingsCount: Int? = null,
        @SerializedName("genre")
        val genre: String? = null,
        @SerializedName("genre_slush")
        val genreSlush: String? = null,
        @SerializedName("geo")
        val geo: String? = null,
        @SerializedName("id")
        val id: String? = null,
        @SerializedName("key")
        val key: String? = null,
        @SerializedName("license")
        val license: String? = null,
        @SerializedName("liked")
        val liked: Boolean? = null,
        @SerializedName("permalink")
        val permalink: String? = null,
        @SerializedName("permalink_url")
        val permalinkUrl: String? = null,
        @SerializedName("playback_count")
        val playbackCount: Int? = null,
        @SerializedName("played")
        val played: Boolean? = null,
        @SerializedName("preview_url")
        val previewUrl: String? = null,
        @SerializedName("private")
        val private: String? = null,
        @SerializedName("release_date")
        val releaseDate: String? = null,
        @SerializedName("release_timestamp")
        val releaseTimestamp: Int? = null,
        @SerializedName("reshared")
        val reshared: Boolean? = null,
        @SerializedName("reshares_count")
        val resharesCount: Int? = null,
        @SerializedName("stream_url")
        val streamUrl: String? = null,
        @SerializedName("taged_artists")
        val tagedArtists: String? = null,
        @SerializedName("tags")
        val tags: String? = null,
        @SerializedName("thumb")
        val thumb: String? = null,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("type")
        val type: String? = null,
        @SerializedName("uri")
        val uri: String? = null,
        @SerializedName("user")
        val user: User? = null,
        @SerializedName("user_id")
        val userId: String? = null,
        @SerializedName("version")
        val version: String? = null,
        @SerializedName("waveform_data")
        val waveformData: String? = null,
        @SerializedName("waveform_url")
        val waveformUrl: String? = null
    ) {
        data class User(
            @SerializedName("avatar_url")
            val avatarUrl: String? = null,
            @SerializedName("caption")
            val caption: String? = null,
            @SerializedName("id")
            val id: String? = null,
            @SerializedName("permalink")
            val permalink: String? = null,
            @SerializedName("permalink_url")
            val permalinkUrl: String? = null,
            @SerializedName("uri")
            val uri: String? = null,
            @SerializedName("username")
            val username: String? = null
        )
    }
}


fun TrackList.toArtistList(): List<PopularArtist> {
    return groupBy { it.userId }.map { PopularArtist(
        it.value[0].user?.username ?: "",
        it.value[0].user?.permalink ?: "",
        it.value[0].user?.avatarUrl ?: "",
        it.value.sumOf { it.playbackCount ?: 0 },
        it.value.sumOf { it.favoritingsCount ?: 0}
    ) }
}

fun TrackList.toSongList(): List<ArtistSong> {
    return map { ArtistSong(
        it.title ?: "Unknown",
        it.artworkUrl ?: "",
        it.bpm ?: "",
        it.description ?: "",
        it.duration?.toInt() ?: 0,
        it.genre ?: "",
        it.releaseTimestamp ?: 0,
        it.streamUrl ?: ""
    ) }
}

