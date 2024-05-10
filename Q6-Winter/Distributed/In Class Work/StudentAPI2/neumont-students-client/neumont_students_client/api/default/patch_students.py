from http import HTTPStatus
from typing import Any, Dict, List, Optional, Union, cast

import httpx

from ...client import AuthenticatedClient, Client
from ...types import Response, UNSET
from ... import errors

from typing import cast
from typing import cast, List
from typing import Dict
from ...models.student import Student



def _get_kwargs(
    *,
    client: Client,
    json_body: List['Student'],

) -> Dict[str, Any]:
    url = "{}/students".format(
        client.base_url)

    headers: Dict[str, str] = client.get_headers()
    cookies: Dict[str, Any] = client.get_cookies()

    

    

    

    json_json_body = []
    for componentsschemasstudent_list_item_data in json_body:
        componentsschemasstudent_list_item = componentsschemasstudent_list_item_data.to_dict()

        json_json_body.append(componentsschemasstudent_list_item)






    

    return {
	    "method": "patch",
        "url": url,
        "headers": headers,
        "cookies": cookies,
        "timeout": client.get_timeout(),
        "json": json_json_body,
    }


def _parse_response(*, client: Client, response: httpx.Response) -> Optional[Union[Any, List['Student']]]:
    if response.status_code == HTTPStatus.OK:
        response_200 = []
        _response_200 = response.json()
        for componentsschemasstudent_list_item_data in (_response_200):
            componentsschemasstudent_list_item = Student.from_dict(componentsschemasstudent_list_item_data)



            response_200.append(componentsschemasstudent_list_item)

        return response_200
    if response.status_code == HTTPStatus.METHOD_NOT_ALLOWED:
        response_405 = cast(Any, None)
        return response_405
    if client.raise_on_unexpected_status:
        raise errors.UnexpectedStatus(f"Unexpected status code: {response.status_code}")
    else:
        return None


def _build_response(*, client: Client, response: httpx.Response) -> Response[Union[Any, List['Student']]]:
    return Response(
        status_code=HTTPStatus(response.status_code),
        content=response.content,
        headers=response.headers,
        parsed=_parse_response(client=client, response=response),
    )


def sync_detailed(
    *,
    client: Client,
    json_body: List['Student'],

) -> Response[Union[Any, List['Student']]]:
    """Update 1 or more students

     Updates 1 or more students in the database

    Args:
        json_body (List['Student']):

    Raises:
        errors.UnexpectedStatus: If the server returns an undocumented status code and Client.raise_on_unexpected_status is True.
        httpx.TimeoutException: If the request takes longer than Client.timeout.

    Returns:
        Response[Union[Any, List['Student']]]
    """


    kwargs = _get_kwargs(
        client=client,
json_body=json_body,

    )

    response = httpx.request(
        verify=client.verify_ssl,
        **kwargs,
    )

    return _build_response(client=client, response=response)

def sync(
    *,
    client: Client,
    json_body: List['Student'],

) -> Optional[Union[Any, List['Student']]]:
    """Update 1 or more students

     Updates 1 or more students in the database

    Args:
        json_body (List['Student']):

    Raises:
        errors.UnexpectedStatus: If the server returns an undocumented status code and Client.raise_on_unexpected_status is True.
        httpx.TimeoutException: If the request takes longer than Client.timeout.

    Returns:
        Response[Union[Any, List['Student']]]
    """


    return sync_detailed(
        client=client,
json_body=json_body,

    ).parsed

async def asyncio_detailed(
    *,
    client: Client,
    json_body: List['Student'],

) -> Response[Union[Any, List['Student']]]:
    """Update 1 or more students

     Updates 1 or more students in the database

    Args:
        json_body (List['Student']):

    Raises:
        errors.UnexpectedStatus: If the server returns an undocumented status code and Client.raise_on_unexpected_status is True.
        httpx.TimeoutException: If the request takes longer than Client.timeout.

    Returns:
        Response[Union[Any, List['Student']]]
    """


    kwargs = _get_kwargs(
        client=client,
json_body=json_body,

    )

    async with httpx.AsyncClient(verify=client.verify_ssl) as _client:
        response = await _client.request(
            **kwargs
        )

    return _build_response(client=client, response=response)

async def asyncio(
    *,
    client: Client,
    json_body: List['Student'],

) -> Optional[Union[Any, List['Student']]]:
    """Update 1 or more students

     Updates 1 or more students in the database

    Args:
        json_body (List['Student']):

    Raises:
        errors.UnexpectedStatus: If the server returns an undocumented status code and Client.raise_on_unexpected_status is True.
        httpx.TimeoutException: If the request takes longer than Client.timeout.

    Returns:
        Response[Union[Any, List['Student']]]
    """


    return (await asyncio_detailed(
        client=client,
json_body=json_body,

    )).parsed

