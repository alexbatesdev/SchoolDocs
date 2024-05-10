import pytest
import main  # Import the module to test


@pytest.mark.parametrize(
    ("target", "expected"), [(5, 2), (10, 3), (16, 4), (29, 5), (80, 6)]
)
class TestMain:
    @pytest.fixture
    def test_list(self):
        return [2, 4, 5, 10, 16, 29, 80]

    def test_linear_search_for_existing_item(self, target, expected, test_list):
        assert main.linearSearch(test_list, target) == expected

    def test_linear_search_for_non_existing_item(self, target, expected, test_list):
        assert main.linearSearch(test_list, target) == expected

    def test_linear_search_for_non_existing_item_smallest(
        self, target, expected, test_list
    ):
        assert main.linearSearch(test_list, target) == expected

    def test_linear_search_for_non_existing_item_largest(
        self, target, expected, test_list
    ):
        assert main.linearSearch(test_list, target) == expected

    def test_binary_search_for_existing_item(self, target, expected, test_list):
        assert main.binarySearch(test_list, target) == expected

    def test_binary_search_for_non_existing_item(self, target, expected, test_list):
        assert main.binarySearch(test_list, target) == expected

    def test_binary_search_for_non_existing_item_smallest(
        self, target, expected, test_list
    ):
        assert main.binarySearch(test_list, target) == expected

    def test_binary_search_for_non_existing_item_largest(
        self, target, expected, test_list
    ):
        assert main.binarySearch(test_list, target) == expected
