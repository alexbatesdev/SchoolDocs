from http import HTTPStatus
from typing import Any, Dict, List, Optional, Union, cast

import httpx

from ...client import AuthenticatedClient, Client
from ...types import Response, UNSET
from ... import errors

from typing import cast
from typing import Dict
from ...models.student import Student



def _get_kwargs(
    student_id: int,
    *,
    client: Client,
    json_body: Student,

) -> Dict[str, Any]:
    url = "{}/students/{studentId}".format(
        client.base_url,studentId=student_id)

    headers: Dict[str, str] = client.get_headers()
    cookies: Dict[str, Any] = client.get_cookies()

    

    

    

    json_json_body = json_body.to_dict()



    

    return {
	    "method": "patch",
        "url": url,
        "headers": headers,
        "cookies": cookies,
        "timeout": client.get_timeout(),
        "json": json_json_body,
    }


def _parse_response(*, client: Client, response: httpx.Response) -> Optional[Union[Any, Student]]:
    if response.status_code == HTTPStatus.OK:
        response_200 = Student.from_dict(response.json())



        return response_200
    if response.status_code == HTTPStatus.NOT_FOUND:
        response_404 = cast(Any, None)
        return response_404
    if response.status_code == HTTPStatus.METHOD_NOT_ALLOWED:
        response_405 = cast(Any, None)
        return response_405
    if client.raise_on_unexpected_status:
        raise errors.UnexpectedStatus(f"Unexpected status code: {response.status_code}")
    else:
        return None


def _build_response(*, client: Client, response: httpx.Response) -> Response[Union[Any, Student]]:
    return Response(
        status_code=HTTPStatus(response.status_code),
        content=response.content,
        headers=response.headers,
        parsed=_parse_response(client=client, response=response),
    )


def sync_detailed(
    student_id: int,
    *,
    client: Client,
    json_body: Student,

) -> Response[Union[Any, Student]]:
    """Update a student by id

     Updates a student by id

    Args:
        student_id (int):
        json_body (Student):

    Raises:
        errors.UnexpectedStatus: If the server returns an undocumented status code and Client.raise_on_unexpected_status is True.
        httpx.TimeoutException: If the request takes longer than Client.timeout.

    Returns:
        Response[Union[Any, Student]]
    """


    kwargs = _get_kwargs(
        student_id=student_id,
client=client,
json_body=json_body,

    )

    response = httpx.request(
        verify=client.verify_ssl,
        **kwargs,
    )

    return _build_response(client=client, response=response)

def sync(
    student_id: int,
    *,
    client: Client,
    json_body: Student,

) -> Optional[Union[Any, Student]]:
    """Update a student by id

     Updates a student by id

    Args:
        student_id (int):
        json_body (Student):

    Raises:
        errors.UnexpectedStatus: If the server returns an undocumented status code and Client.raise_on_unexpected_status is True.
        httpx.TimeoutException: If the request takes longer than Client.timeout.

    Returns:
        Response[Union[Any, Student]]
    """


    return sync_detailed(
        student_id=student_id,
client=client,
json_body=json_body,

    ).parsed

async def asyncio_detailed(
    student_id: int,
    *,
    client: Client,
    json_body: Student,

) -> Response[Union[Any, Student]]:
    """Update a student by id

     Updates a student by id

    Args:
        student_id (int):
        json_body (Student):

    Raises:
        errors.UnexpectedStatus: If the server returns an undocumented status code and Client.raise_on_unexpected_status is True.
        httpx.TimeoutException: If the request takes longer than Client.timeout.

    Returns:
        Response[Union[Any, Student]]
    """


    kwargs = _get_kwargs(
        student_id=student_id,
client=client,
json_body=json_body,

    )

    async with httpx.AsyncClient(verify=client.verify_ssl) as _client:
        response = await _client.request(
            **kwargs
        )

    return _build_response(client=client, response=response)

async def asyncio(
    student_id: int,
    *,
    client: Client,
    json_body: Student,

) -> Optional[Union[Any, Student]]:
    """Update a student by id

     Updates a student by id

    Args:
        student_id (int):
        json_body (Student):

    Raises:
        errors.UnexpectedStatus: If the server returns an undocumented status code and Client.raise_on_unexpected_status is True.
        httpx.TimeoutException: If the request takes longer than Client.timeout.

    Returns:
        Response[Union[Any, Student]]
    """


    return (await asyncio_detailed(
        student_id=student_id,
client=client,
json_body=json_body,

    )).parsed

