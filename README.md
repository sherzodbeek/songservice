# songservice
Song Service

## Sub-task 2: Song Service

For the **Song Service**, it is recommended to implement a simple CRUD service to manage the song record (metadata).
The service should provide the ability to manage some metadata about the songs (artist, album, etc.).
Make sure the service is still available over HTTP.

**Service definition could be next:**

<table>
    <tr>
        <td><b>POST /songs</b></td>
        <td colspan="6"><b>Create a new song metadata record in database</b></td>
    </tr>
    <tr>
        <td rowspan="2"><b>Request</b></td>
        <td><i>Parameter</i></td>
        <td><i>Description</i></td>
        <td colspan="2"><i>Body example</i></td>
        <td><i>Description</i></td>
        <td><i>Restriction</i></td>
    </tr>
    <tr>
        <td></td>
        <td></td>
        <td colspan="2"><p>{</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;"name": "We are the champions",</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;"artist": "Queen",</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;"album": "News of the world",</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;"length": "2:59",</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;"resourceId": "123",</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;"year": ""1977</p>
            <p>}</p>
        </td>
        <td>Song metadata record, referencing to resource id (mp3 file itself)</td>
        <td>MP3 audio data</td>
    </tr>
    <tr>
        <td rowspan="2"><b>Response</b></td>
        <td><i>Body</i></td>
        <td><i>Description</i></td>
        <td colspan="4"><i>Code</i></td>
    </tr>
    <tr>
        <td><p>{</p>
            <p>&nbsp;&nbsp;&nbsp;&nbsp;"id":1123</p>
            <p>}</p></td>
        <td>Integer id – ID of the created song metadata</td>
        <td colspan="4"><p>200 – OK</p>
                        <p>400 – Song metadata missing validation error</p>
                        <p>500 – An internal server error has occurred</p>
        </td>
    </tr>
    <tr>
        <td><b>GET /songs/{id}</b></td>
        <td colspan="6"><b>Get song metadata</b></td>
    </tr>
    <tr>
        <td rowspan="2"><b>Request</b></td>
        <td><i>Parameter</i></td>
        <td><i>Description</i></td>
        <td><i>Restriction</i></td>
        <td><i>Body example</i></td>
        <td><i>Description</i></td>
        <td><i>Restriction</i></td>
    </tr>
    <tr>
        <td>Integer id</td>
        <td>Song metadata ID to get</td>
        <td>ID of an existing song metadata</td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="2"><b>Response</b></td>
        <td colspan="2"><i>Body</i></td>
        <td colspan="2"><i>Description</i></td>
        <td colspan="2"><i>Code</i></td>
    </tr>
    <tr>
        <td colspan="2"><p>{</p>
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;"name": "We are the champions",</p>
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;"artist": "Queen",</p>
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;"album": "News of the world",</p>
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;"length": "2:59",</p>
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;"resourceId": "123",</p>
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;"year": ""1977</p>
                        <p>}</p>
        </td>
        <td colspan="2"></td>
        <td colspan="2"><p>200 – OK</p>
                        <p>404 – The song metadata with the specified id does not exist</p>
                        <p>500 – An internal server error has occurred</p>
        </td>
    </tr>
    <tr>
        <td><b>DELETE /songs?id=1,2</b></td>
        <td colspan="6"><b>Delete a song(s) metadata. If there is no song metadata for id, do nothing</b></td>
    </tr>
    <tr>
        <td rowspan="2"><b>Request</b></td>
        <td><i>Parameter</i></td>
        <td><i>Description</i></td>
        <td><i>Restriction</i></td>
        <td><i>Body example</i></td>
        <td><i>Description</i></td>
        <td><i>Restriction</i></td>
    </tr>
    <tr>
        <td>String id</td>
        <td>CSV of song metadata IDs to remove</td>
        <td>Valid CSV length < 200 characters</td>
        <td></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td rowspan="2"><b>Response</b></td>
        <td colspan="2"><i>Body</i></td>
        <td colspan="2"><i>Description</i></td>
        <td colspan="2"><i>Code</i></td>
    </tr>
    <tr>
        <td colspan="2"><p>{</p>
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;"ids": [1,2]</p>
                        <p>}</p></td>
        <td colspan="2">Integer [] ids - IDs of deleted resources</td>
        <td colspan="2"><p>200 – OK</p>
                        <p>500 – An internal server error has occurred</p>
        </td>
    </tr>
</table>
