package co.novu.api

import co.novu.dto.request.UpdateNotificationTemplateStatusRequest
import co.novu.dto.response.NotificationTemplates
import co.novu.dto.response.PaginatedResponseWrapper
import co.novu.dto.response.ResponseWrapper
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigInteger

interface NotificationTemplatesApi {

    @GET("notification-templates")
    suspend fun getNotificationTemplates(@Query("page") page: BigInteger? = BigInteger.valueOf(1), @Query("limit") limit: BigInteger? = BigInteger.valueOf(10)): Response<PaginatedResponseWrapper<NotificationTemplates>>

    @POST("notification-templates")
    suspend fun createNotificationTemplates(@Body request: NotificationTemplates): Response<ResponseWrapper<NotificationTemplates>>

    @PUT("notification-templates/{templateId}")
    suspend fun updateNotificationTemplates(@Path("templateId") templateId: String, @Body request: NotificationTemplates): Response<ResponseWrapper<NotificationTemplates>>

    @DELETE("notification-templates/{templateId}")
    suspend fun deleteNotificationTemplate(@Path("templateId") templateId: String): Response<ResponseWrapper<Boolean>>

    @GET("notification-templates/{templateId}")
    suspend fun getNotificationTemplate(@Path("templateId") templateId: String): Response<ResponseWrapper<NotificationTemplates>>

    @PUT("notification-templates/{templateId}/status")
    suspend fun updateNotificationTemplateStatus(@Path("templateId") templateId: String, @Body request: UpdateNotificationTemplateStatusRequest): Response<ResponseWrapper<NotificationTemplates>>
}
