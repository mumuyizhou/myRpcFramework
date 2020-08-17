package serializers;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ZhouPan
 * @date 2020-08-17
 */
public class FastJsonSerializeTool implements ISerializer {

	@Override
	public String serialize(Object obj) {
		return JSONObject.toJSONString(obj);
	}

	@Override
	public Object deserialize(String str, Class<?> clazz) {
		return JSONObject.parseObject(str, clazz);
	}
}
