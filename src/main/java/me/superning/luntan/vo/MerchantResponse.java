package me.superning.luntan.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建商户的响应对象
 * @author superning
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantResponse {
    /**json的反序列化需要无参构造 商户的主键id 创建失败为 -1 */
    private Long id;
}
