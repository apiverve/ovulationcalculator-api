Pod::Spec.new do |s|
  s.name             = 'APIVerveOvulationCalculator'
  s.version          = '1.2.3'
  s.summary          = 'Ovulation Calculator estimates ovulation dates, fertile windows, and provides detailed cycle phase information based on menstrual cycle...'
  s.description      = <<-'DESC'
Ovulation Calculator estimates ovulation dates, fertile windows, and provides detailed cycle phase information based on menstrual cycle data.

This iOS/macOS SDK provides a convenient way to access the Ovulation Calculator from APIVerve.
Supports both callback-based and async/await patterns.
                       DESC

  s.homepage         = 'https://apiverve.com/marketplace/ovulationcalculator?utm_source=cocoapods&utm_medium=referral&utm_content=homepage'
  s.license          = { :type => 'MIT', :file => 'LICENSE' }
  s.author           = { 'APIVerve' => 'hello@apiverve.com' }
  # The SDK repo holds every language; the pod lives under cocoapods/ with its own tag.
  s.source           = { :git => 'https://github.com/apiverve/ovulationcalculator-api.git', :tag => "cocoapods/v#{s.version}" }

  s.ios.deployment_target = '15.0'
  s.osx.deployment_target = '12.0'

  s.swift_version = '5.0'

  s.source_files = 'cocoapods/Sources/APIVerveOvulationCalculator/**/*.swift'

  s.frameworks = 'Foundation'

  # APIResponse, APIVerveError and the request plumbing, shared by every APIVerve pod.
  s.dependency 'APIVerveCore', '~> 1.2'
end
