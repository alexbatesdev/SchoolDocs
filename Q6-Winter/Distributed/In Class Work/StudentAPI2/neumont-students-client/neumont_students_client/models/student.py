from typing import Any, Dict, Type, TypeVar, Tuple, Optional, BinaryIO, TextIO, TYPE_CHECKING

from typing import List


import attr

from ..types import UNSET, Unset

from typing import Union
from ..types import UNSET, Unset





T = TypeVar("T", bound="Student")

@attr.s(auto_attribs=True)
class Student:
    """
    Attributes:
        name (Union[Unset, str]):
        age (Union[Unset, int]):
        id (Union[Unset, int]):
        gpa (Union[Unset, float]):
    """

    name: Union[Unset, str] = UNSET
    age: Union[Unset, int] = UNSET
    id: Union[Unset, int] = UNSET
    gpa: Union[Unset, float] = UNSET
    additional_properties: Dict[str, Any] = attr.ib(init=False, factory=dict)


    def to_dict(self) -> Dict[str, Any]:
        name = self.name
        age = self.age
        id = self.id
        gpa = self.gpa

        field_dict: Dict[str, Any] = {}
        field_dict.update(self.additional_properties)
        field_dict.update({
        })
        if name is not UNSET:
            field_dict["name"] = name
        if age is not UNSET:
            field_dict["age"] = age
        if id is not UNSET:
            field_dict["id"] = id
        if gpa is not UNSET:
            field_dict["gpa"] = gpa

        return field_dict



    @classmethod
    def from_dict(cls: Type[T], src_dict: Dict[str, Any]) -> T:
        d = src_dict.copy()
        name = d.pop("name", UNSET)

        age = d.pop("age", UNSET)

        id = d.pop("id", UNSET)

        gpa = d.pop("gpa", UNSET)

        student = cls(
            name=name,
            age=age,
            id=id,
            gpa=gpa,
        )

        student.additional_properties = d
        return student

    @property
    def additional_keys(self) -> List[str]:
        return list(self.additional_properties.keys())

    def __getitem__(self, key: str) -> Any:
        return self.additional_properties[key]

    def __setitem__(self, key: str, value: Any) -> None:
        self.additional_properties[key] = value

    def __delitem__(self, key: str) -> None:
        del self.additional_properties[key]

    def __contains__(self, key: str) -> bool:
        return key in self.additional_properties
