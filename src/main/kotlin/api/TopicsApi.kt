package co.novu.api

import co.novu.dto.request.CreateByNameRequest
import co.novu.dto.request.CreateTopicRequest
import co.novu.dto.response.AddSubscribersResponse
import co.novu.dto.response.CheckTopicSubscriberResponse
import co.novu.dto.response.CreateTopicResponse
import co.novu.dto.response.PaginatedResponseWrapper
import co.novu.dto.response.ResponseWrapper
import co.novu.dto.response.SubscriberList
import co.novu.dto.response.TopicResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigInteger

interface TopicsApi {

    @GET("topics")
    suspend fun filterTopics(
        @Query("page") page: BigInteger? = null,
        @Query("pageSize")pageSize: BigInteger? = null,
        @Query("key") key: String? = null
    ): Response<PaginatedResponseWrapper<TopicResponse>>

    @POST("topics")
    suspend fun createTopic(@Body request: CreateTopicRequest): Response<ResponseWrapper<CreateTopicResponse>>

    @POST("topics/{topicKey}/subscribers")
    suspend fun addSubscriber(@Path("topicKey") topicKey: String, @Body request: SubscriberList): Response<ResponseWrapper<AddSubscribersResponse>>

    @POST("topics/{topicKey}/subscribers/removal")
    suspend fun removeSubscribers(@Path("topicKey") topicKey: String, @Body request: SubscriberList): Response<Unit>

    @GET("topics/{topicKey}/subscribers/{externalSubscriberId}")
    suspend fun checkSubscriber(@Path("topicKey") topicKey: String, @Path("externalSubscriberId") externalSubscriberId: String): Response<CheckTopicSubscriberResponse>

    @GET("topics/{topicKey}")
    suspend fun getTopic(@Path("topicKey") topicKey: String): Response<ResponseWrapper<TopicResponse>>

    @PATCH("topics/{topicKey}")
    suspend fun renameTopic(@Path("topicKey") topicKey: String, @Body request: CreateByNameRequest): Response<ResponseWrapper<TopicResponse>>

    @DELETE("topics/{topicKey}")
    suspend fun deleteTopic(@Path("topicKey") topicKey: String): Response<Unit>
}
