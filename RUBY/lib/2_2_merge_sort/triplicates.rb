require_relative 'merge_sort'

# Public: Determine if there is any element
# common to all given lists (case-sensitive)
# of Comparable elements, and if so, return
# first such element.
#
# lists - Zero or more lists to search for
# common element in.
#
# Examples
#
#   a = ["ccc", "aaa", "bbb"]
#   b = ["kkk", "bbb", "jjj"]
#   c = ["bbb", "zzz", "yyy"]
#   common_elem(a, b, c)
#   # => "bbb"
#
# Returns the Comparable common element or
#   nil if none was found
def common_elem(*lists)
  return nil if lists.size <= 1

  # Sort input lists
  lists.each do |list|
    merge_sort!(list)
  end

  # Find first common element in sorted lists
  find_common_elem(lists)
end

private

# Having all lists sorted, take first
# elements of each list, find min element
# of those.
#
# In the list with min element move on to
# the next element getting next set of
# elements, where find min element, and
# so on, until 3 equal min elements found.
#
# To eliminate the case, when duplicates
# in one list might be defined as common elements,
# there is a skipping stage, checking if next
# element after current min is equal to min,
# and if so, incrementing the list's index.
def find_common_elem(given_lists)
  lists_total_size = given_lists.map(&:size).sum

  # Indices of all lists to move through them
  indices = Array.new(given_lists.size, 0)

  # Equal elements counter - inc when coincidence found
  # (1  - for current element)
  eql_elems = 1

  # Previous minimum initial value
  prev_min = ""


  lists_total_size.times do
    # Take first elements of sorted lists
    # and find min out of those
    curr_min_elems = Array.new

    given_lists.each_with_index do |list, list_number|
      if indices[list_number] < list.size
        curr_min_elems << list[indices[list_number]]
      else
        curr_min_elems << nil
      end
    end

    # Current min element out of all lists
    curr_min = curr_min_elems.reject(&:nil?).min
    return nil if curr_min == nil

    # In a list with min element found inc the index
    # while elements after min are equal to min
    # (to eliminate counting duplicates in one list
    # as common elements)
    curr_min_elems.each_with_index do |elem, list_number|
      next if elem == nil

      # Define the list containing min element
      if elem == curr_min

        # List with min element (for readability)
        list = given_lists[list_number]

        # Skipping stage
        while list[indices[list_number]] == curr_min do
          indices[list_number] += 1
        end

        # Break when min found
        break
      end
    end

    # Count equal elements
    if curr_min == prev_min
      eql_elems += 1
    else
      eql_elems = 1
    end

    # Update previous element
    prev_min = curr_min

    # Exit when first m (m - number of lists given)
    # equal elements in different lists are found
    return curr_min if eql_elems == given_lists.size
  end

  # If no common elements found
  nil
end

a = ["abcd", "bcde", "cdef"]
b = ["cdef", "cdef", "efgh"]
c = ["aghi", "bhij", "ckef"]

p a.inspect
p b.inspect
p c.inspect
puts
p "triplicate = #{common_elem(a, b, c)}"
