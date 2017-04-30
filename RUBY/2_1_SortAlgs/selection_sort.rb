def sorted?(a)
  (0...a.size - 1).all? { |i| a[i] <= a[i+1] }
end

def selection_sort(a)
  n = a.size
  (0...n).each do |i|
    min = (i...n).min { |p, q| a[p] <=> a[q] }
    a[i], a[min] = a[min], a[i]
  end
end

def selection_sort2(a)
  n = a.size
  (0...n).each do |i|
    min = (i...n).min do |p, q|
      get_var = lambda {|item| block_given? ? yield(item) : item}
      var1 = get_var.call(a[p])
      var2 = get_var.call(a[q])
      var1 <=> var2
    end

    a[i], a[min] = a[min], a[i]
  end
end

a = [5,3,2,1,4]
puts sorted?(a)
selection_sort(a)
raise unless sorted?(a)
puts a.inspect
puts sorted?(a)

a = [['Vasya', 1], ['Petya', 3], ['Kolya', 2]]
selection_sort2(a) { |item| -item[1] }
puts a.inspect # [["Petya", 3], ["Kolya", 2], ["Vasya", 1]]
