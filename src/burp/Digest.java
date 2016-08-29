package burp;

@FunctionalInterface
public interface Digest {
    String create(String message);
}
