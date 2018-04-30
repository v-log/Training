require_relative "../2_1_sort_algs/insertion_sort"

def sorted?(a)
  (0...a.size - 1).all? { |i| a[i] <= a[i + 1] }
end

def merge_sort!(a, cutoff = 16, &block)
  return a if a.size <= 1

  # Auxiliary array for merge sort
  aux = Array.new(a.size)

  # Set the right initial position of arrays
  # in order to merge from source to axiliary
  # array at the bottom level merge
  level = levels(a)
  a, aux = aux, a if level%2 != 0

  if block_given?
    merge_sort_hlpr!(a, 0, a.size - 1, aux, level, cutoff, &block)
  else
    merge_sort_hlpr!(a, 0, a.size - 1, aux, level, cutoff, &Proc.new {|x| x})
  end

  # Duplicate arrays in case we get to return
  # the unsorted one after the last merge
  (0...a.length).each do |x|
    a[x] = aux[x]
  end
end

private

def levels(array)
  # Count source array depth to set the 
  # right initial position

  #The first level is there whatever the case
  levels = 1
  pow_of_2 = 1

  while array.size > pow_of_2 do
    pow_of_2 *= 2
    levels += 1
  end
  levels
end

def merge_sort_hlpr!(a, lo, hi, aux, level, cutoff, &block)
  level -= 1

  # Improvement 1
  # Choose insertion sort if optimal

  # Add 1 to count the zero index
  if hi - lo + 1 <= cutoff
    # Set the right position of source and
    # destination arrays depending on level parity
    # (need this due to improvement 3)
    if level.even?
      insertion_sort_2!(aux, lo, hi, a, &block)
    else
      insertion_sort_2!(a, lo, hi, aux, &block)
    end

    level += 1
    return
  end

  # Return when reach bottom level (1 element)
  if hi <= lo
    # If array size is odd, one element, which is
    # supposed to appear at bottom level, will
    # do one level higher, which means it must be
    # merged with something, but there is nil.
    # We omit this merge by copying only existing
    # element.
    aux[lo] = a[lo] if level != 0
    level += 1
    return
  end

  mid = lo + (hi - lo)/2

  # Improvement 3 - change between arrays
  # in order to avoid unnecessary data copy
  merge_sort_hlpr!(aux, lo, mid, a, level, cutoff, &block)
  merge_sort_hlpr!(aux, mid + 1, hi, a, level, cutoff, &block)

  # Improvement 2
  # Check if subarrays are already in order
  if block.call(a[mid]) > block.call(a[mid + 1])
    merge!(a, lo, mid, hi, aux, &block)
  end

  level += 1
end

def merge!(a, lo, mid, hi, aux, &block)
  # Return if merging single element
  if hi <= lo
    return
  end

  i = lo
  j = mid + 1

  (lo..hi).each do |x|
    if i > mid
      aux[x] = a[j]
      j += 1
    elsif j > hi
      aux[x] = a[i]
      i += 1
    elsif block.call(a[j]) < block.call(a[i])
      aux[x] = a[j]
      j += 1
    else
      aux[x] = a[i]
      i += 1
    end
  end
end

a = (0...32).to_a.shuffle
puts a.inspect
puts sorted?(a)
merge_sort!(a)
puts a.inspect
raise unless sorted?(a)
puts "result:" + "\n" + a.inspect
puts sorted?(a).to_s
puts 
b = [['Vasya', 1], ['Petya', 3], ['Kolya', 2]]
puts b.inspect
merge_sort!(b) { |item| -item[1] }
puts "result:" + "\n" + b.inspect # [["Petya", 3], ["Kolya", 2], ["Vasya", 1]]
