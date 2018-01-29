def sorted?(a)
  (0...a.size - 1).all? { |i| a[i] <= a[i + 1] }
end

def merge_sort!(a)
  return a if a.size <= 1
  aux = Array.new(a.size)
  if block_given?
    merge_sort_hlpr!(a, 0, a.size - 1, aux, &Proc.new)
  else
    merge_sort_hlpr!(a, 0, a.size - 1, aux)
  end

  a
end

private

def merge_sort_hlpr!(a, lo, hi, aux)
  if hi <= lo
    return a
  end

  mid = lo + (hi - lo)/2
  if block_given?
    merge_sort_hlpr!(a, lo, mid, aux, &Proc.new)
    merge_sort_hlpr!(a, mid + 1, hi, aux, &Proc.new)
    merge_2!(a, lo, mid, hi, aux, &Proc.new)
  else
    merge_sort_hlpr!(a, lo, mid, aux)
    merge_sort_hlpr!(a, mid + 1, hi, aux)
    merge_2!(a, lo, mid, hi, aux)
  end

  a
end

def merge_2! (a, lo, mid, hi, aux)
  i = lo
  j = mid + 1
  (lo..hi).each do |x|
    aux[x] = a[x]
  end

  get_var = block_given? ? lambda { |item| yield (item) } : lambda { |item| item }

  (lo..hi).each do |x|
    if i > mid
      a[x] = aux[j]
      j += 1
    elsif j > hi
      a[x] = aux[i]
      i += 1
    elsif get_var[aux[j]] < get_var[aux[i]]
      a[x] = aux[j]
      j += 1
    else
      a[x] = aux[i]
      i += 1
    end
  end

  a
end

a = (0..30).to_a.shuffle
puts a.inspect
puts sorted?(a)
merge_sort!(a)
raise unless sorted?(a)
puts "result:" + "\n" + a.inspect
puts sorted?(a).to_s
puts b = [['Vasya', 1], ['Petya', 3], ['Kolya', 2]]
puts b.inspect
merge_sort!(b) { |item| -item[1] }
puts "result:" + "\n" + b.inspect # [["Petya", 3], ["Kolya", 2], ["Vasya", 1]]
puts
c = (0..30).to_a.shuffle
puts c.inspect
puts sorted?(c)
merge_sort!(c)
raise unless sorted?(c)
puts "result:" + "\n" + c.inspect
puts sorted?(c).to_s
