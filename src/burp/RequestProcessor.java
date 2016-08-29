package burp;

import java.util.ArrayList;
import java.util.List;

class RequestProcessor{

    private IHttpRequestResponse request;
    private IExtensionHelpers h;

    RequestProcessor(IHttpRequestResponse requestResponse, IBurpExtenderCallbacks extenderCallbacks) {

        request = requestResponse;
        h = extenderCallbacks.getHelpers();

    }

    // This method appends a "&md5=[MD5 goes here]" at the end of the request line in a http request
    void appendDigest(Digest d, String parameterName) {
        IRequestInfo info = h.analyzeRequest(request);
        List<IParameter> params = info.getParameters();

        //Iterate over parameters and add the to a list
        List<String> values = new ArrayList<>();
        params.forEach((p) -> {
            if (!p.getName().equals(parameterName)) values.add(p.getValue());
        });
        String[] a = {};
        a = values.toArray(a);
        String digest = d.create(Util.arrayToString(a, "&"));

        byte[] req = request.getRequest();
        //Get the possible old MAC parameter
        IParameter oldParameter = h.getRequestParameter(req, parameterName);
        //Build a replacement for it with the new value
        IParameter newParameter = h.buildParameter(parameterName, digest, IParameter.PARAM_URL);
        if ( oldParameter != null) {
            request.setRequest(h.updateParameter(req, newParameter));
        } else {
            request.setRequest(h.addParameter(req, newParameter));
        }
    }
}