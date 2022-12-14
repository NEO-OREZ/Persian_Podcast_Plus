package com.NEO_OREZ.persianpodcastplus.Apollo

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.apollographql.apollo3.network.okHttpClient
import com.NEO_OREZ.persianpodcastplus.*
import com.NEO_OREZ.persianpodcastplus.adapters.RecyclerAdapter
import com.NEO_OREZ.persianpodcastplus.adapters.RecyclerAdapterCat
import com.NEO_OREZ.persianpodcastplus.adapters.RecyclerAdapterEpisode
import com.NEO_OREZ.persianpodcastplus.adapters.RecyclerAdapterFirst
import com.NEO_OREZ.persianpodcastplus.type.*
import okhttp3.OkHttpClient

class CallRequest {

    private var userKey = "964974a7-93ae-4df5-88d4-be9be9569e83"
    private var userSecret = "N7MaoHCnxZJDQovx539x6kax8kRJlBFhtfOKHAhI"
    private val baseURL = "https://api.podchaser.com/graphql"
    private val launch = ArrayList<DataQuery.Data1>()
    private val launchHot = ArrayList<DataQueryHOTQuery.Data1>()
    private val launchCat = ArrayList<DataCategoriesQuery.Data1>()
    private val launchEpisode = ArrayList<DataQEpisodeQuery.Data1>()



    suspend fun apolloToken() : String {
        val apolloClient = ApolloClient.Builder()
            .serverUrl(baseURL)
            .build()

        val responseToken0 = apolloClient.mutation(
            TokenMutation(
                AccessTokenRequest(userKey, userSecret, grant_type = OAuthGrantType.CLIENT_CREDENTIALS)
            )
        ).execute()
        val Token0 = responseToken0.data!!.requestAccessToken!!.access_token
       // MainActivity().saveData(Token0)
        Log.d("logCall_token", Token0)
        return Token0
    }
    //////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
     suspend fun apolloDataMain(token0:String): ArrayList<DataQuery.Data1> {
        //20 Megabyte of memory cache
        val cacheFactory = MemoryCacheFactory(maxSizeBytes = 20 * 1024 * 1024)
        val sqlCacheFactory = SqlNormalizedCacheFactory("apollo_main.db")

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(token0))
            .build()
        val apolloClient = ApolloClient.Builder()
            .serverUrl(baseURL)
            .okHttpClient(okHttpClient)
            .normalizedCache(cacheFactory)
            .normalizedCache(sqlCacheFactory)
            .build()

        val responseData = apolloClient.query(
            DataQuery(
                podcastsFilters = Optional.presentIfNotNull(PodcastFilters(language = Optional.presentIfNotNull("FA")))
                , podcastsSort = Optional.presentIfNotNull(PodcastSort(Optional.presentIfNotNull(SortDirection.DESCENDING)
                    ,PodcastSortType.FOLLOWER_COUNT))
                , podcastsFirst = Optional.presentIfNotNull(8)
                , podcastsPage = Optional.presentIfNotNull(0)
                , podcastsPaginationType = Optional.presentIfNotNull(PaginationType.PAGE)
            )
        ).execute()

        val  dataPods  = responseData.data!!.podcasts!!.data as ArrayList
         //Log.d("apollo3", dataPods.toString())
        launch.addAll(dataPods)
        RecyclerAdapter(launch).notifyDataSetChanged()
        return dataPods
    }
//////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////
    suspend fun apolloDataHot(token0:String): ArrayList<DataQueryHOTQuery.Data1> {
        //20 Megabyte of memory cache
        val cacheFactory = MemoryCacheFactory(maxSizeBytes = 20 * 1024 * 1024)
        val sqlCacheFactory = SqlNormalizedCacheFactory("apollo_hot.db")

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(token0))
            .build()
        val apolloClient = ApolloClient.Builder()
            .serverUrl(baseURL)
            .okHttpClient(okHttpClient)
            .normalizedCache(cacheFactory)
            .normalizedCache(sqlCacheFactory)
            .build()

        val responseDataHot = apolloClient.query(
            DataQueryHOTQuery(
                filters = Optional.presentIfNotNull(PodcastFilters(language = Optional.presentIfNotNull("FA")))
                , podcastsFirst2 = Optional.presentIfNotNull(8)
                , page = Optional.presentIfNotNull(0)
                , podcastsSort2 = Optional.presentIfNotNull(PodcastSort(
                    Optional.presentIfNotNull(SortDirection.ASCENDING)
                    , PodcastSortType.DATE_OF_FIRST_EPISODE))
            )
        ).execute()

        val  dataPods  = responseDataHot.data?.podcasts?.data as ArrayList
        launchHot.addAll(dataPods)
        RecyclerAdapterFirst(launchHot).notifyDataSetChanged()
        return dataPods
    }
    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    suspend fun apolloDataCat(token0:String, categoryText:String?): ArrayList<DataCategoriesQuery.Data1>{
        val cacheFactory = MemoryCacheFactory(maxSizeBytes = 20 * 1024 * 1024)
        val sqlCacheFactory = SqlNormalizedCacheFactory("apollo_cat.db")
        val categoryList = listOf(categoryText)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(token0))
            .build()
        val apolloClient = ApolloClient.Builder()
            .serverUrl(baseURL)
            .okHttpClient(okHttpClient)
            .normalizedCache(cacheFactory)
            .normalizedCache(sqlCacheFactory)
            .build()
        val responseDataCat = apolloClient.query(
            DataCategoriesQuery(
                filters = Optional.presentIfNotNull(PodcastFilters(categories = Optional.presentIfNotNull(categoryList)))
                , page = Optional.presentIfNotNull(1)
                , first = Optional.presentIfNotNull(10)
                , sort = Optional.presentIfNotNull(PodcastSort(
                   Optional.presentIfNotNull(SortDirection.safeValueOf("DESCENDING"))
                    ,PodcastSortType.FOLLOWER_COUNT))
            )
        ).execute()

        val  dataPods  = responseDataCat.data?.podcasts?.data as ArrayList
        //Log.d("logapolloCat",dataPods.toString())
        launchCat.addAll(dataPods)
        RecyclerAdapterCat(launchCat).notifyDataSetChanged()
        return dataPods
    }
    //////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////
    suspend fun apolloDataEpisode(token0:String, ID:String): ArrayList<DataQEpisodeQuery.Data1>{
        val cacheFactory = MemoryCacheFactory(maxSizeBytes = 20 * 1024 * 1024)
        val sqlCacheFactory = SqlNormalizedCacheFactory("apollo_episode.db")

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthorizationInterceptor(token0))
            .build()
        val apolloClient = ApolloClient.Builder()
            .serverUrl(baseURL)
            .okHttpClient(okHttpClient)
            .normalizedCache(cacheFactory)
            .normalizedCache(sqlCacheFactory)
            .build()
        val responseDataEpisode = apolloClient.query(
            DataQEpisodeQuery(
                identifier = PodcastIdentifier(ID ,PodcastIdentifierType.PODCHASER),
                sort = Optional.presentIfNotNull(EpisodeSort(
                    Optional.presentIfNotNull(
                        SortDirection.DESCENDING),EpisodeSortType.AIR_DATE)))
        ).execute()

        Log.d("logapolloCat",responseDataEpisode.data.toString())

        val  dataPods  = responseDataEpisode.data?.podcast?.episodes?.data as ArrayList
        Log.d("logapolloCat",dataPods.toString())
        launchEpisode.addAll(dataPods)
        RecyclerAdapterEpisode(launchEpisode).notifyDataSetChanged()
        return dataPods
    }
}