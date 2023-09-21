package co.novu.api

import co.novu.dto.request.BulkSubscriberRequest
import co.novu.dto.request.MarkMessageActionAsSeenRequest
import co.novu.dto.request.MarkSubscriberFeedAsRequest
import co.novu.dto.request.SubscriberRequest
import co.novu.dto.request.UpdateSubscriberCredentialsRequest
import co.novu.dto.request.UpdateSubscriberOnlineStatusRequest
import co.novu.dto.request.UpdateSubscriberRequest
import co.novu.dto.response.CreateBulkSubscriberResponse
import co.novu.dto.response.PaginatedResponseWrapper
import co.novu.dto.response.ResponseWrapper
import co.novu.dto.response.SubscriberDeleteResponse
import co.novu.dto.response.SubscriberNotificationResponse
import co.novu.dto.response.SubscriberPreferenceResponse
import co.novu.dto.response.SubscriberResponse
import co.novu.dto.response.UnseenNotificationsCountResponse
import co.novu.dto.response.UpdateSubscriberPreferencesRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigInteger

interface SubscribersApi {

    @GET("subscribers")
    suspend fun getSubscribers(@Query("page") page: BigInteger?): Response<PaginatedResponseWrapper<SubscriberResponse>>

    @POST("subscribers")
    suspend fun createSubscriber(@Body subscriberRequest: SubscriberRequest): Response<ResponseWrapper<SubscriberResponse>>

    @POST("subscribers/bulk")
    suspend fun createSubscriberBulk(@Body request: BulkSubscriberRequest): Response<CreateBulkSubscriberResponse>

    @GET("subscribers/{subscriberId}")
    suspend fun getSubscriber(@Path("subscriberId") subscriberId: String): Response<ResponseWrapper<SubscriberResponse>>

    @PUT("subscribers/{subscriberId}")
    suspend fun updateSubscriber(@Path("subscriberId") subscriberId: String, @Body request: UpdateSubscriberRequest): Response<ResponseWrapper<SubscriberResponse>>

    @DELETE("subscribers/{subscriberId}")
    suspend fun deleteSubscriber(@Path("subscriberId") subscriberId: String): Response<ResponseWrapper<SubscriberDeleteResponse>>

    @PUT("subscribers/{subscriberId}/credentials")
    suspend fun updateSubscriberCredentials(@Path("subscriberId") subscriberId: String, @Body request: UpdateSubscriberCredentialsRequest): Response<ResponseWrapper<SubscriberResponse>>

    @PATCH("subscribers/{subscriberId}/online-status")
    suspend fun updateSubscriberOnlineStatus(@Path("subscriberId")subscriberId: String, @Body request: UpdateSubscriberOnlineStatusRequest): Response<ResponseWrapper<SubscriberResponse>>

    @GET("subscribers/{subscriberId}/preferences")
    suspend fun getSubscriberPreferences(@Path("subscriberId") subscriberId: String): Response<ResponseWrapper<List<SubscriberPreferenceResponse>>>

    @PATCH("subscribers/{subscriberId}/preferences/{templateId}")
    suspend fun updateSubscriberPreferences(
        @Path("subscriberId") subscriberId: String,
        @Path("templateId") templateId: String,
        @Body request: UpdateSubscriberPreferencesRequest
    ): Response<ResponseWrapper<SubscriberPreferenceResponse>>

    @GET("subscribers/{subscriberId}/notifications/feed")
    suspend fun getSubscriberNotificationsFeed(@Path("subscriberId") subscriberId: String): Response<PaginatedResponseWrapper<SubscriberNotificationResponse>>

    @GET("subscribers/{subscriberId}/notifications/unseen")
    suspend fun getSubscriberUnseenNotificationsCount(@Path("subscriberId") subscriberId: String): Response<ResponseWrapper<UnseenNotificationsCountResponse>>

    @POST("subscribers/{subscriberId}/messages/markAs")
    suspend fun markSubscriberMessageFeedAs(
        @Path("subscriberId") subscriberId: String,
        @Body request: MarkSubscriberFeedAsRequest
    ): Response<ResponseWrapper<SubscriberNotificationResponse>>

    @POST("subscribers/{subscriberId}/messages/{messageId}/actions/{type}")
    suspend fun markMessageActionAsSeen(
        @Path("subscriberId") subscriberId: String,
        @Path("messageId") messageId: String,
        @Path("type") type: String,
        @Body request: MarkMessageActionAsSeenRequest
    ): Response<ResponseWrapper<SubscriberNotificationResponse>>
}
