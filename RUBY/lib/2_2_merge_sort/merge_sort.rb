require_relative "../2_1_sort_algs/insertion_sort"

def sorted?(a)
  (0...a.size - 1).all? { |i| a[i] <= a[i + 1] }
end

def merge_sort!(a, &block)
  return a if a.size <= 1
  aux = Array.new(a.size)

  if block_given?
    merge_sort_hlpr!(a, 0, a.size - 1, aux, &block)
  else
    merge_sort_hlpr!(a, 0, a.size - 1, aux, &Proc.new {|x| x})
  end
  a
end

private

#improvement 1
def choose_sort(a, lo,  hi, aux, &block)
  if hi - lo <= 16
    insertion_sort2!(a, &block)
  else
    merge_sort_hlpr!(a, lo, hi, aux, &block)
  end
end

#improvement 3
def swap(a, b)
  a, b = b, a
end

def merge_sort_hlpr!(a, lo, hi, aux, &block)
  if hi <= lo
    return a
  end

  mid = lo + (hi - lo)/2
  
  puts a.inspect
  puts aux.inspect
  puts

  merge_sort_hlpr!(a, lo, mid, aux, &block)
  swap(a, aux)

  puts "after swap 1"
  puts a.inspect
  puts aux.inspect
  puts

  merge_sort_hlpr!(a, mid + 1, hi, aux, &block)
  swap(a, aux)

  puts "after swap 2"
  puts a.inspect
  puts aux.inspect
  puts

  #choose_sort(a, lo, mid, aux, &block)
  #choose_sort(a, mid + 1, hi, aux, &block)

  #improvement 2
  if block.call(a[mid]) > block.call(a[mid + 1])
    merge!(a, lo, mid, hi, aux, &block)
    swap(a, aux)

    puts "after swap 3"
    puts a.inspect
    puts aux.inspect
    puts

  end

  a
end

def merge! (a, lo, mid, hi, aux, &block)
  i = lo
  j = mid + 1
#  (lo..hi).each do |x|
#    aux[x] = a[x]
#  end

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
  a
end

a = (0...8).to_a.shuffle
puts a.inspect
puts sorted?(a)
merge_sort!(a)
raise unless sorted?(a)
puts "result:" + "\n" + a.inspect
puts sorted?(a).to_s
puts 
b = [['Vasya', 1], ['Petya', 3], ['Kolya', 2]]
puts b.inspect
merge_sort!(b) { |item| -item[1] }
puts "result:" + "\n" + b.inspect # [["Petya", 3], ["Kolya", 2], ["Vasya", 1]]

