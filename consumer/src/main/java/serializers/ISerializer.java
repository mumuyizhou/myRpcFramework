package serializers;

/**
 * @author ZhouPan
 * @date 2020-08-17
 */
public interface ISerializer {
	String serialize(Object obj);
	Object deserialize(String str, Class<?> clazz);
}
